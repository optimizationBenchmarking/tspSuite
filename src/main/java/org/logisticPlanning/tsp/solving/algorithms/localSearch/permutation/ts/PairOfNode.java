package org.logisticPlanning.tsp.solving.algorithms.localSearch.permutation.ts;

public class PairOfNode {
	private int a, b;


	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public PairOfNode() {
		this.a = 0;
		this.b = 1;
	}

	public PairOfNode(int a, int b) {
		this.a = a;
		this.b = b;
	}

	public final boolean equals(final Object o) {
		if(o==this)return true;
		else if(o instanceof PairOfNode){
			PairOfNode temp = (PairOfNode)o;
			if((this.a == temp.a && this.b == temp.b)
					|| (this.a == temp.b && this.b == temp.a))
				return true;
			else return false;
		}
		else return false;
	}
	
	public PairOfNode clone(){
		PairOfNode res = new PairOfNode(this.a, this.b);
		return res;
	}
}
