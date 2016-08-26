package com.tao.ml.common;

public class JMatrix {
	double[][] _data;
	private int _x;
	private int _y;
	double arrangerResult=1.0;
	public JMatrix(int x,int y,boolean malloc) {
		set_x(x);
		set_y(y);
		if(malloc) _data = new double[get_y()][get_x()];
	}
	public JMatrix(double[][] data) {
		_data = data;
		set_x(data[0].length);
		set_y(data.length);
	}

	public double[][] getData() {
		return _data;
	}
	
	public double det2(){//Guass 消去
		double k=0;
		for (int p = 0; p<_data[0].length-1; p++) {   
			for (int r =p+1; r<_data.length; r++) {
				k=_data[r][p]/_data[p][p];
				_data[r][p]=0;
				for (int c = p+1; c<_data[0].length; c++) {
					_data[r][c]=_data[r][c]-k*_data[p][c];
				}//u
			}//r
		}//c
		double[][] arrangerMatrix=new double[_data.length][_data[0].length]; 
		for (int i = 0; i<_data.length; i++) {
			for (int j = 0; j<_data[0].length; j++) {
				arrangerMatrix[j]=_data[j];
				if (i==j) {arrangerResult=arrangerResult*_data[j][j];}//计算主对角线相乘的结果
			}//j
		}//i
		return arrangerResult;
	}

	public double det() {
		if(_x!=_y)
			return 0;
		double res=0;
		for(int i=0;i<_x;i++) {
			double t=1;
			for(int j=0;j<_x;j++) {
				t=t*_data[(i+j)%_x][j];
			}
			res+=t;
			t=1;
			for(int j=0;j<_x;j++) {
				t=t*_data[_x-1-j][(i+j)%_x];
			}
			res-=t;
		}
		return res;	
	}
	
	public JMatrix plus(JMatrix mat) {
		if(mat.get_x()!=_x||mat.get_y()!=_y) {
			return null;
		}
		JMatrix rMat = new JMatrix(_x,_y,true);

		for(int i=0;i<get_y();i++) {
			for(int j=0;j<get_x();j++) {
				rMat.getData()[i][j]=_data[i][j]+mat.getData()[i][j];
			}
		}
		return rMat;
	}
	
	public JMatrix minus(JMatrix mat) {
		if(mat.get_x()!=_x||mat.get_y()!=_y) {
			return null;
		}
		JMatrix rMat = new JMatrix(_x,_y,true);

		for(int i=0;i<get_y();i++) {
			for(int j=0;j<get_x();j++) {
				rMat.getData()[i][j]=_data[i][j]-mat.getData()[i][j];
			}
		}
		return rMat;
	}
	
	public double sum(double[][] data1,double[][] data2,int y,int x,int n) {
		double sum=0;
		for(int i=0;i<n;i++) {
			sum+=data1[y][i]*data2[i][x];
		}
		return sum;
	}
	
	public JMatrix multiply(JMatrix mat) {
		if(mat.get_y()!=_x) {
			return null;
		}
		JMatrix rMat = new JMatrix(mat.get_x(),_y,true);
		
		for(int i=0;i<_y;i++) {
			for(int j=0;j<get_x();j++) {
				rMat.getData()[i][j]=sum(_data,mat.getData(),i,j,_x);
			}
		}
		return rMat;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<get_y();i++) {
			for(int j=0;j<get_x();j++) {
				sb.append(_data[i][j]);
				sb.append(',');
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private int get_x() {
		return _x;
	}

	private void set_x(int _x) {
		this._x = _x;
	}

	public int get_y() {
		return _y;
	}

	public void set_y(int _y) {
		this._y = _y;
	}


	
}
