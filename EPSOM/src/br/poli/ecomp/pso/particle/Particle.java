package br.poli.ecomp.pso.particle;

import java.util.Random;

import br.poli.ecomp.pso.clpso.ParticleCLPSO;
import br.poli.ecomp.pso.fdrpso.ParticleFDRPSO;
import br.poli.ecomp.pso.hpsotvac.ParticleHSPSOTVAC;
import br.poli.ecomp.pso.lips.ParticleLIPS;
import br.poli.ecomp.pso.wpso.ParticleWPSO;

public abstract class Particle implements Cloneable {

	public static Particle[] particles;

	protected double x_max, x_min, v_max, v_min;
	protected static double multiply_vmin = 1E-10;
	protected static double multiply_vmax = (1.0/100.0);

	public static Particle gBestParticle;
	public static double gBestFitness;

	public double[] position, pBestPosition, velocity;	
	protected double fitness, bestFitness;

	public Particle () {}

	public Particle (int dimension, PSOType type, FunctionEnum function, int index) {
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
			this.position[d] = x_min/2 + gerador.nextDouble() * (x_max - x_min)/2;
			this.velocity[d] = -v_max/2 + gerador.nextDouble() * v_max / 2;
		}

		//this.calcularFitness(function);
	}

	public double getPosition (int dimension) {
		return position[dimension];
	}

	public void setVelocity (double velocity, int dimension) {
		this.velocity[dimension] = velocity;
		
		if (Math.abs(this.velocity[dimension]) > v_max) {
			this.velocity[dimension] = Math.signum(this.velocity[dimension]) * v_max;
		} else if (Math.abs(this.velocity[dimension]) < v_min) {
			this.velocity[dimension] = Math.signum(this.velocity[dimension]) * v_min;
		}
	}

	public double getVelocity (int dimension) {
		return velocity[dimension];
	}

	public double getPBest (int dimension) {
		return pBestPosition[dimension];
	}

	public double getFitness () {
		return fitness;
	}
	
	public double getBestFitness () {
		return bestFitness;
	}
		
	public double getVMax () {
		return this.v_max;
	}

	public abstract void updateVelocity (int iteration) throws Exception;

	public void updatePosition () {
		for (int d = 0; d < this.position.length; d++) {
			this.position[d] = this.position[d] + this.velocity[d];
			if (this.position[d] > x_max) {
				this.position[d] = x_max;
			} else if (this.position[d] < x_min) {
				this.position[d] = x_min;
			}
		}
	}

	public void calcularFitness (FunctionEnum function) {
		
		try {
			Function.calculateFunction(function, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (this.fitness < this.bestFitness) {
			this.bestFitness = this.fitness;
			this.pBestPosition = this.position.clone();
		}
	}

	public static void changeParticlesType (PSOType type) {

		Particle[] cloneParticles = Particle.particles.clone();

		if (type == PSOType.WPSO) {
			Particle.particles = new ParticleWPSO[PSO.getNumParticles()];
		} else if (type == PSOType.FDR_PSO) {
			Particle.particles = new ParticleFDRPSO[PSO.getNumParticles()];
		} else if (type == PSOType.HPSO_TVAC) {
			Particle.particles = new ParticleHSPSOTVAC[PSO.getNumParticles()];
		} else if (type == PSOType.LIPS) {
			Particle.particles = new ParticleLIPS[PSO.getNumParticles()];
		} else if (type == PSOType.CLPSO) {
			Particle.particles = new ParticleCLPSO[PSO.getNumParticles()];
		}

		for (int i = 0; i < cloneParticles.length; i++) {
			Particle particle = cloneParticles[i];

			if (type == PSOType.WPSO) {
				ParticleWPSO p = new ParticleWPSO();
				p.copyOf(particle);
				Particle.particles[i] = p;
			} else if (type == PSOType.FDR_PSO) {
				ParticleFDRPSO p = new ParticleFDRPSO();
				p.copyOf(particle);
				Particle.particles[i] = p;
			} else if (type == PSOType.HPSO_TVAC) {
				ParticleHSPSOTVAC p = new ParticleHSPSOTVAC();
				p.copyOf(particle);
				Particle.particles[i] = p;
			} else if (type == PSOType.LIPS) {
				ParticleLIPS p = new ParticleLIPS();
				p.copyOf(particle);
				Particle.particles[i] = p;
			} else if (type == PSOType.CLPSO) {
				ParticleCLPSO p = new ParticleCLPSO(PSO.getNumDimension(), PSO.getFunction(), (i + 1));
				p.copyOf(particle);
				Particle.particles[i] = p;				 
			}
		}
	}

	public void copyOf(Particle particle) {
		this.position = particle.position.clone();
		this.velocity = particle.velocity.clone();
		this.fitness = particle.fitness;
		this.bestFitness = particle.bestFitness;
		this.pBestPosition = particle.pBestPosition.clone();
		this.v_max = particle.v_max;
		this.v_min = particle.v_min;
		this.x_max = particle.x_max;
		this.x_min = particle.x_min;
	}
	
	public static int indexOf (Particle particle) {
		Particle[] particles = Particle.particles;
		for (int i = 0; i < particles.length; i++) {
			if (Particle.particles[i] == particle) {
				return i;
			}
		}
		
		return -1;
	}

}
