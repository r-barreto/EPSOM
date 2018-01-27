package br.poli.ecomp.pso.particle;

public abstract class PSO {
	
	protected static int numParticles;
	protected static int numIteration;
	protected static int numDimension;
	protected static int numSimulation;
	protected static FunctionEnum function;
	
	public static int getNumParticles() {
		return numParticles;
	}
	public static int getNumIteration() {
		return numIteration;
	}
	public static int getNumDimension() {
		return numDimension;
	}
	public static int getNumSimulation() {
		return numSimulation;
	}
	public static FunctionEnum getFunction() {
		return function;
	}	
	
	public abstract PSOResult call (int numParticles, int numIteration, int numDimension, int numSimulation, FunctionEnum function) throws Exception;
	
}
