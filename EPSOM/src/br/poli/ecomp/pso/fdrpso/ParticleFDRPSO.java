package br.poli.ecomp.pso.fdrpso;

import java.util.Random;

import br.poli.ecomp.pso.particle.Function;
import br.poli.ecomp.pso.particle.FunctionEnum;
import br.poli.ecomp.pso.particle.PSO;
import br.poli.ecomp.pso.particle.Particle;;

public class ParticleFDRPSO extends Particle {

	public static double c3;					//Constantes
	public static double c1, c2;			// Constantes
	public static double wf, wi;
	public static double w;

	public ParticleFDRPSO () {}

	public ParticleFDRPSO (int dimension, FunctionEnum function, int index) {
		this.position = new double[dimension];
		this.pBestPosition = new double[dimension];
		this.velocity = new double[dimension];
		this.fitness = this.bestFitness = Double.MAX_VALUE;

		Random gerador = new Random();

		x_max = Function.FunctionMax(function);
		x_min = Function.FunctionMin(function);
		v_max = (x_max - x_min) * multiply_vmax;
		v_min = v_max * multiply_vmin;
		
		w = wf;

		for (int d = 0; d < dimension; d++) {
			this.position[d] = x_min / 2.0 + gerador.nextDouble() * (x_max - x_min) / 2.0;
			this.velocity[d] = - (v_max / 2.0) + gerador.nextDouble() * (v_max);
		}

		//this.calcularFitness(function);
	}

	@Override
	public void updateVelocity (int iteration) throws Exception {
		Random gerador = new Random();
		
		w = (w - wi) * (((double) PSO.getNumIteration() - iteration)) / (((double) PSO.getNumIteration() + wi)); 

		for (int d = 0; d < PSO.getNumDimension(); d++) {
			
			Particle nBest = null;
			double diff = Double.MAX_VALUE;
			
			for (int j = 0; j < PSO.getNumParticles(); j++) {
				if ((ParticleFDRPSO.particles[j].getFitness() - this.getFitness())/Math.abs(ParticleFDRPSO.particles[j].getPosition(d) - getPosition(d)) < diff) {
					diff = (ParticleFDRPSO.particles[j].getFitness() - this.getFitness())/(Math.abs(ParticleFDRPSO.particles[j].getPosition(d) - getPosition(d)));
					nBest = ParticleFDRPSO.particles[j];
				}
			}

			if (nBest == null) {
				//System.out.println("NBest nulo.");
				nBest = this;
				//System.exit(0);
			}
			
			double rand1 = gerador.nextDouble();
			double rand2 = gerador.nextDouble();
			//double rand3 = gerador.nextDouble();

			this.velocity[d] = w * this.velocity[d] +
					c1 * rand1 * (this.pBestPosition[d] - this.position[d]) + 
					c2 * rand2 * (gBestParticle.getPosition(d) - this.position[d]) +
					c3 * (nBest.getPosition(d) - this.position[d]);
			if (Math.abs(this.velocity[d]) > v_max) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_max;
			} else if (Math.abs(this.velocity[d]) < v_min) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_min;
			}
		}
	}

	@Override
	public ParticleFDRPSO clone() {
		ParticleFDRPSO p = new ParticleFDRPSO();
		p.position = this.position.clone();
		p.fitness = this.fitness;
		return p;
	}

}
