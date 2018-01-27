package br.poli.ecomp.pso.particle;

public class PSOResult {
	
	public String pso;
	public double average;
	public double best;
	public double std_variation;
	
	public PSOResult () {
		best = Double.MAX_VALUE;
		average = 0;
		std_variation = 0;
	}
	
}
