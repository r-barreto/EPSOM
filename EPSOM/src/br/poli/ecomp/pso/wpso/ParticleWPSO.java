package br.poli.ecomp.pso.wpso;

import java.util.Random;

import br.poli.ecomp.pso.particle.Function;
import br.poli.ecomp.pso.particle.FunctionEnum;
import br.poli.ecomp.pso.particle.PSO;
import br.poli.ecomp.pso.particle.Particle;

public class ParticleWPSO extends Particle {

	public static double c1, c2;			// Constantes
	public static double wf, wi;
	private double w;

	public ParticleWPSO () {}

	public ParticleWPSO (int dimension, FunctionEnum function, int index) {
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
	
	@Override
	public void updateVelocity (int iteration) throws Exception {
		Random gerador = new Random();
		
		this.w = wf - wi * (((double) iteration + 1) / PSO.getNumIteration());
		
		for (int d = 0; d < this.velocity.length; d++) {
			double rand1 = gerador.nextDouble();
			double rand2 = gerador.nextDouble();
			
			this.velocity[d] = this.w * this.velocity[d] +
					c1 * rand1 * (this.pBestPosition[d] - this.position[d]) + 
					c2 * rand2 * (Particle.gBestParticle.getPosition(d) - this.position[d]);
			
			if (Math.abs(this.velocity[d]) > v_max) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_max;
			} else if (Math.abs(this.velocity[d]) < v_min) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_min;
			}
		}
	}

	@Override
	public ParticleWPSO clone() {
		ParticleWPSO p = new ParticleWPSO();
		p.position = this.position.clone();
		p.fitness = this.fitness;
		return p;
	}

}
