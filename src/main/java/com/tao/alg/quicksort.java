package com.tao.alg;

public class quicksort {

	public static void main(String[] args) {
		int[] arr = {3,5,6,1,2,8,7,4};
		
		int ret = sort(arr,0,arr.length -1);

		System.out.println(ret);
		
		for(int i:arr)
			System.out.print(i+"  ");
	}

	public static void exchange(int[] arr,int n0,int n1) {
		int tmp = arr[n0];
		arr[n0] = arr[n1];
		arr[n1] = tmp;
	}
	
	static int sort(int[] arr,int _head,int _tail) {
		if(arr==null||_tail-_head<=0) {
			return -1;
		}
		if(_tail-_head==1) {
			if(arr[_head]>arr[_tail]) {
				exchange(arr,_head,_tail);
			}
			return -1;
		}
		int head = _head;
		int tail = _tail;
		
		int x=arr[head];
		boolean flag=true;
		while(head!=tail) {
			if(flag) {
				if(arr[tail]>x) {
					tail--;
					continue;
				} else {
					arr[head]=arr[tail];
					head++;
				}
			} else {
				if(arr[head]<x) {
					head++;
					continue;
				} else {
					arr[tail]=arr[head];
					tail--;
				}
			}
			flag=!flag;
		}
		arr[head] = x;
		
		sort(arr,_head,head-1);
		sort(arr,head+1,_tail);
		return head;
	}
}
