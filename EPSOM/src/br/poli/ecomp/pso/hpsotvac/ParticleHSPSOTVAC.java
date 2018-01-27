package br.poli.ecomp.pso.hpsotvac;

import java.util.Random;

import br.poli.ecomp.pso.particle.Function;
import br.poli.ecomp.pso.particle.FunctionEnum;
import br.poli.ecomp.pso.particle.PSO;
import br.poli.ecomp.pso.particle.Particle;

public class ParticleHSPSOTVAC extends Particle {

	public static double v, c1f, c2f;	//TVAC
	public static double c3, maxValue;	// Constantes
	public static double c1, c2;			// Constantes
	public static double gBestFitnessVariation;

	//private double pm, m;
	
	public ParticleHSPSOTVAC () {}

	public ParticleHSPSOTVAC (int dimension, FunctionEnum function, int index) {
		this.position = new double[dimension];
		this.pBestPosition = new double[dimension];
		this.velocity = new double[dimension];
		this.fitness = this.bestFitness = Double.MAX_VALUE;

		Random gerador = new Random();

		x_max = Function.FunctionMax(function);
		x_min = Function.FunctionMin(function);
		v_max = (x_max - x_min) * multiply_vmax;
		v_min = v_max * multiply_vmin;

		//this.pm = gerador.nextDouble();

		for (int d = 0; d < dimension; d++) {
			this.position[d] = x_min / 2.0 + gerador.nextDouble() * (x_max - x_min) / 2.0;
			this.velocity[d] = - (v_max / 2.0) + gerador.nextDouble() * (v_max);
		}

		//this.calcularFitness(function);
	}

	@Override
	public void updateVelocity (int iteration) throws Exception {
		Random gerador = new Random();

		double c1 = (c1f - ParticleHSPSOTVAC.c1) * (((double) iteration / PSO.getNumIteration())) + ParticleHSPSOTVAC.c1;
		double c2 = (c2f - ParticleHSPSOTVAC.c2) * (((double) iteration / PSO.getNumIteration())) + ParticleHSPSOTVAC.c2;

		for (int d = 0; d < PSO.getNumDimension(); d++) {
			double rand1 = gerador.nextDouble();
			double rand2 = gerador.nextDouble();

			this.velocity[d] = c1 * rand1 * (this.pBestPosition[d] - this.position[d]) + 
					c2 * rand2 * (gBestParticle.getPosition(d) - this.position[d]);

			if (this.velocity[d] == 0.0) {

				double r3 = gerador.nextDouble();
				if (r3 < 0.5) {
					double r4 = gerador.nextDouble();
					this.velocity[d] = v * r4; 
				} else {
					double r5 = gerador.nextDouble();
					this.velocity[d] = - v * r5;
				}							
			}

			if (Math.abs(this.velocity[d]) > v_max) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_max;
			} else if (Math.abs(this.velocity[d]) < v_min) {
				this.velocity[d] = Math.signum(this.velocity[d]) * v_min;
			}
		}

		/*
		Particle k = ParticleHSPSOTVAC.particles[(int) (gerador.nextDouble() * (PSO.getNumParticles() - 1))];
		int l = (int) (gerador.nextDouble() * (position.length - 1));
		
		if (gBestFitnessVariation == 0) {
			double r1 = gerador.nextDouble();
			double r2 = gerador.nextDouble();

			if (r1 < this.pm) {
				if (r2 < 0.5) {
					double r3 = gerador.nextDouble();
					k.setVelocity(k.getVelocity(l) + r3 * getVMax() / this.m, l);
				} else {
					double r4 = gerador.nextDouble();
					k.setVelocity(k.getVelocity(l) - r4 * getVMax() / this.m, l);					
				}
			}

		}
		*/

	}

	@Override
	public ParticleHSPSOTVAC clone() {
		ParticleHSPSOTVAC p = new ParticleHSPSOTVAC();
		p.position = this.position.clone();
		p.fitness = this.fitness;
		return p;
	}

}
