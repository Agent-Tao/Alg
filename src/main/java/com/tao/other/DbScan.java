package com.tao.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Iterator;

public class DbScan {

	double Eps=3;   //区域半径
    int MinPts=4;   //密度
     
    //由于自己到自己的距离是0,所以自己也是自己的neighbor
    public Vector<DataObject> getNeighbors(DataObject p,ArrayList<DataObject> objects){
        Vector<DataObject> neighbors=new Vector<DataObject>();
        Iterator<DataObject> iter=objects.iterator();
        while(iter.hasNext()){
            DataObject q=iter.next();
            double[] arr1=p.getVector();
            double[] arr2=q.getVector();
            int len=arr1.length;
            
            double dis=0;
          if((dis = Global.calEuraDist(arr1, arr2, len))<=Eps){    //使用欧氏距离   
                neighbors.add(q);
            }
        }
        return neighbors;
    }
     
    public int dbscan(ArrayList<DataObject> objects){
        int clusterID=0;
        boolean AllVisited=false;
        while(!AllVisited){
            Iterator<DataObject> iter=objects.iterator();
            while(iter.hasNext()){
            	//遍历所有对象
                DataObject p=iter.next();
                //访问过，跳过
                if(p.isVisited())
                    continue;
                AllVisited=false;
                p.setVisited(true);     //设为visited后就已经确定了它是核心点还是边界点
                Vector<DataObject> neighbors=getNeighbors(p,objects);
                if(neighbors.size()<MinPts){
                    if(p.getCid()<=0)
                        p.setCid(-1);       //cid初始为0,表示未分类；分类后设置为一个正数；设置为-1表示噪声。
                }else{
                    if(p.getCid()<=0){
                        clusterID++;
                        expandCluster(p,neighbors,clusterID,objects);
                    }else{
                        int iid=p.getCid();
                        expandCluster(p,neighbors,iid,objects);
                    }
                }      
            }
            AllVisited=true;
        }
        return clusterID;
    }
    //扩展cluster
    private void expandCluster(DataObject p, Vector<DataObject> neighbors,
            int clusterID,ArrayList<DataObject> objects) {
    	//设置id
        p.setCid(clusterID);
        	
        Iterator<DataObject> iter=neighbors.iterator();
        //遍历邻居
        while(iter.hasNext()){
            DataObject q=iter.next();
            if(!q.isVisited()){
                q.setVisited(true);
                Vector<DataObject> qneighbors=getNeighbors(q,objects);
                if(qneighbors.size()>=MinPts){
                    Iterator<DataObject> it=qneighbors.iterator();
                    while(it.hasNext()){
                        DataObject no=it.next();
                        //没被加入任何cluster，加入之
                        if(no.getCid()<=0)
                            no.setCid(clusterID);
                    }
                }
            }
            if(q.getCid()<=0){       //q不是任何簇的成员
                q.setCid(clusterID);
            }
        }
    }
 
    public static void main(String[] args){
    	//DataSource datasource=new DataSource();
    	DbScan ds=new DbScan();
    	
    	ArrayList<DataObject> objects = new ArrayList<DataObject>();
    	objects.add(new DataObject(2,2));
    	objects.add(new DataObject(3,1));
    	objects.add(new DataObject(3,4));
    	objects.add(new DataObject(3,14));
    	
    	objects.add(new DataObject(5,3));
    	
    	objects.add(new DataObject(8,3));
    	objects.add(new DataObject(8,6));
    	
    	objects.add(new DataObject(9,8));
    	
    	objects.add(new DataObject(10,4));
    	objects.add(new DataObject(10,7));
    	objects.add(new DataObject(10,10));
    	objects.add(new DataObject(10,14));
    	
    	objects.add(new DataObject(11,13));
    	
    	objects.add(new DataObject(12,8));
    	objects.add(new DataObject(12,15));
    	
    	objects.add(new DataObject(14,7));
    	objects.add(new DataObject(14,9));
    	objects.add(new DataObject(14,15));
    	objects.add(new DataObject(15,8));
    	
    	int clunum=ds.dbscan(objects);
    	
    	for(DataObject o: objects) {
    		o.print();
    	}
    }

}
