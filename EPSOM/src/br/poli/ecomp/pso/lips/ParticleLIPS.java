package br.poli.ecomp.pso.lips;

import java.util.Random;
import java.util.TreeMap;

import br.poli.ecomp.pso.particle.Function;
import br.poli.ecomp.pso.particle.FunctionEnum;
import br.poli.ecomp.pso.particle.PSO;
import br.poli.ecomp.pso.particle.Particle;

public class ParticleLIPS extends Particle {

	public static double X;	 //LIPS
	public static double c;
	public static int nsize; //LIPS
	
	//private long currentTime = System.currentTimeMillis();
	//private Random gerador;
	
	public ParticleLIPS () {}

	public ParticleLIPS (int dimension, FunctionEnum function, int index) {
		this.position = new double[dimension];
		this.pBestPosition = new double[dimension];
		this.velocity = new double[dimension];
		this.fitness = this.bestFitness = Double.MAX_VALUE;
		
		Random gerador = new Random();

		x_max = Function.FunctionMax(function);
		x_min = Function.FunctionMin(function);
		v_max = (x_max - x_min) * multiply_vmax;
		v_min = v_max * multiply_vmin;

		for (int d = 0; d < dimension; d++) {
			this.position[d] = x_min / 2.0 + gerador.nextDouble() * (x_max - x_min) / 2.0;
			this.velocity[d] = - (v_max / 2.0) + gerador.nextDouble() * (v_max);
		}
				
		//this.calcularFitness(function);
	}
	
	private double[] multiplyArray (double[] array, double val) {
		double[] clone = array.clone();
		for (int i = 0; i < clone.length; i++) {
			clone[i] *= val;
		}
		return clone;
	}
	
	private double[] sumArray (double[][] array, double val) {
		double[] result = new double[array[0].length];
		for (int j = 0; j < array[0].length; j++) {
			for (int i = 0; i < array.length; i++) {
				result[j] += array[i][j];
			}
			result[j] *= val;
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateVelocity (int iteration) throws Exception {
		Random gerador = new Random();

		/*
		 * Calculate nBest
		 */
		
		/*
		 * Calcula as distancias euclidianas
		 */
		
		TreeMap<Double, Particle> nBest = new TreeMap<Double, Particle>();

		for (int i = 0; i < PSO.getNumParticles(); i++) {
			if (ParticleLIPS.particles[i] == this) {
				continue;
			}
			
			double distance = 0.0;
			Particle p_i = ParticleLIPS.particles[i];
			for (int d = 0; d < PSO.getNumDimension(); d++) {
				distance += Math.pow(p_i.getPosition(d) - this.position[d], 2.0);
			}

			nBest.put(Math.sqrt(distance), p_i);
		}

		if (nBest.size() < ParticleLIPS.nsize) {
			//System.out.println("NBest size menor que nsize (" + nBest.size() + " < " + ParticleLIPS.nsize + ")");
			return ;
			//System.exit(1);
		}
		
		//SortedSet<Double> nBestj = (SortedSet<Double>) nBest.keySet();

		double[] fij = new double[ParticleLIPS.nsize];
		double fi = 0.0;
		
		/*
		 * Calcula o fi e o fi_j
		 */
		
		for (int i = 0; i < ParticleLIPS.nsize; i++) {
			fi += fij[i] = gerador.nextDouble() * (4.1/ ((double) ParticleLIPS.nsize));			
		}

		double[] Pi = new double[this.position.length];
		
		double[][] nBest_fi = new double[ParticleLIPS.nsize][this.position.length];
		
		TreeMap<Double, Particle> nBestj = (TreeMap<Double, Particle>) nBest.clone();
		
		for (int i = 0; i < ParticleLIPS.nsize; i++) {
			nBest_fi[i] = multiplyArray(nBestj.get(nBestj.firstKey()).position, fij[i] / ParticleLIPS.nsize);
			nBestj.remove(nBestj.firstKey());
		}
		
		Pi = sumArray(nBest_fi, 1.0/fi);

		for (int d = 0; d < this.velocity.length; d++) {
			this.velocity[d] = c * ParticleLIPS.X * (this.velocity[d] + fi * (Pi[d] - this.position[d])); 
			
			//double v_max = this.v_max * 100;
			
			if (Math.abs(this.velocity[d]) > v_max) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_max;
			} else if (Math.abs(this.velocity[d]) < v_min) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_min;
			}
		}
	}

	@Override
	public ParticleLIPS clone() {
		ParticleLIPS p = new ParticleLIPS();
		p.position = this.position.clone();
		p.fitness = this.fitness;
		return p;
	}

}
