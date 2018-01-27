package br.poli.ecomp.pso.clpso;

import java.util.Random;

import br.poli.ecomp.pso.particle.Function;
import br.poli.ecomp.pso.particle.FunctionEnum;
import br.poli.ecomp.pso.particle.PSO;
import br.poli.ecomp.pso.particle.Particle;

public class ParticleCLPSO extends Particle{

	public static double c1;			// Constantes
	public static double wf, wi;
	private double w, pc;

	public ParticleCLPSO() {}

	public ParticleCLPSO (int dimension, FunctionEnum function, int index) {
		this.position = new double[dimension];
		this.pBestPosition = new double[dimension];
		this.velocity = new double[dimension];
		this.fitness = this.bestFitness = Double.MAX_VALUE;

		Random gerador = new Random();

		x_max = Function.FunctionMax(function);
		x_min = Function.FunctionMin(function);
		v_max = (x_max - x_min) * multiply_vmax;
		v_min = v_max * multiply_vmin;

		this.pc = 0.05 + 0.45 * (Math.exp(10.0 * ((double) index) /PSO.getNumParticles()) - 1.0) / (Math.exp(10.0) - 1.0);

		for (int d = 0; d < dimension; d++) {
			this.position[d] = x_min / 2.0 + gerador.nextDouble() * (x_max - x_min) / 2.0;
			this.velocity[d] = - (v_max / 2.0) + gerador.nextDouble() * (v_max);
		}

		//this.calcularFitness(function);
	}

	@Override
	public void updateVelocity (int iteration) throws Exception {
		Random gerador = new Random();
		double rand1 = gerador.nextDouble();

		this.w = wf * ((wf - wi) * ((double) (iteration + 1.0)) / PSO.getNumIteration());

		int count_larger_than_pci = 0;
		for (int d = 0; d < PSO.getNumDimension(); d++) {

			if (gerador.nextDouble() > this.pc) {
				this.velocity[d] = this.w * this.velocity[d] + 
						c1 * rand1 * (pBestPosition[d] - this.position[d]);
				count_larger_than_pci ++;
			} else {

				Particle p1 = ParticleCLPSO.particles[(int) (gerador.nextDouble() * (((double)PSO.getNumParticles() - 1.0)))];
				Particle p2 = ParticleCLPSO.particles[(int) (gerador.nextDouble() * (((double)PSO.getNumParticles() - 1.0)))];

				if(p1.getBestFitness() < p2.getBestFitness()){
					this.velocity[d] = this.w * this.velocity[d] + 
							c1 * rand1 * (p1.getPBest(d) - this.position[d]);
				} else {
					this.velocity[d] = this.w * this.velocity[d] + 
							c1 * rand1 * (p2.getPBest(d) - this.position[d]);
				}
			}

			if (Math.abs(this.velocity[d]) > v_max) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_max;
			} else if (Math.abs(this.velocity[d]) < v_min) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_min;
			}
		}

		if (count_larger_than_pci == PSO.getNumDimension()) {
			int d = (int) (gerador.nextDouble() * (this.velocity.length - 1));
			Particle p = ParticleCLPSO.particles[(int) (gerador.nextDouble() * (PSO.getNumParticles() - 1))];
			this.velocity[d] = this.w * this.velocity[d] + 
					c1 * rand1 * (p.getPosition(d) - this.position[d]);
			
			if (Math.abs(this.velocity[d]) > v_max) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_max;
			} else if (Math.abs(this.velocity[d]) < v_min) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_min;
			}
		}
	}

	@Override
	public ParticleCLPSO clone() {
		ParticleCLPSO p = new ParticleCLPSO();
		p.position = this.position.clone();
		p.fitness = this.fitness;
		return p;
	}

}
