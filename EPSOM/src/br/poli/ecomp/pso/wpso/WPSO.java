package br.poli.ecomp.pso.wpso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.DoubleSummaryStatistics;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import br.poli.ecomp.pso.particle.Function;
import br.poli.ecomp.pso.particle.FunctionEnum;
import br.poli.ecomp.pso.particle.PSO;
import br.poli.ecomp.pso.particle.PSOResult;
import br.poli.ecomp.pso.particle.PSOType;
import br.poli.ecomp.pso.particle.Particle;

public class WPSO extends PSO {

	public static void main(String[] args) throws IOException {

		numParticles = 30;
		numIteration = 10000;
		numDimension = 10;
		numSimulation = 30;
		function = FunctionEnum.Sphere;

		PSOType type = PSOType.WPSO;

		ParticleWPSO.c1 = ParticleWPSO.c2 = 2.0;
		ParticleWPSO.wf = 0.9;
		ParticleWPSO.wi = 0.2;

		File file;
		FileWriter fw = null;

		for (int simulation = 0; simulation < numSimulation; simulation ++) {

			file = new File(type.name() + "_" + function.name() + "_" + "simulation_" + (simulation + 1) + ".txt");
			fw = new FileWriter(file);
			fw.write("function;simulation;iteration;value\n");

			ParticleWPSO.particles = new ParticleWPSO[numParticles];
			ParticleWPSO.gBestParticle = null;
			ParticleWPSO.gBestFitness = Double.MAX_VALUE;

			//Inicializa cada particula randomicamente;
			for (int i = 0; i < ParticleWPSO.particles.length; i++) {
				ParticleWPSO.particles[i] = new ParticleWPSO(numDimension, function, i);

				if (ParticleWPSO.particles[i].getBestFitness() < ParticleWPSO.gBestFitness) {
					ParticleWPSO.gBestFitness = ParticleWPSO.particles[i].getBestFitness();
					ParticleWPSO.gBestParticle = ParticleWPSO.particles[i];
				}
			}

			for (int iteration = 0; iteration < numIteration; iteration++) {
				//Atualiza a velocidade
				for (int i = 0; i < ParticleWPSO.particles.length; i++) {
					try {
						ParticleWPSO.particles[i].updateVelocity(iteration);
					} catch (Exception e) {
						System.out.println("Erro ao atualizar velocidade: ");						
						e.printStackTrace();
					}
				}

				//Atualiza a posição
				for (int i = 0; i < ParticleWPSO.particles.length; i++) {
					ParticleWPSO.particles[i].updatePosition();
				}

				//Atualiza o fitness
				for (int i = 0; i < ParticleWPSO.particles.length; i++) {
					ParticleWPSO.particles[i].calcularFitness(function);
				}

				for (int i = 0; i < ParticleWPSO.particles.length; i++) {
					if (ParticleWPSO.particles[i].getBestFitness() < ParticleWPSO.gBestFitness) {
						ParticleWPSO.gBestFitness = ParticleWPSO.particles[i].getBestFitness();
						ParticleWPSO.gBestParticle = ParticleWPSO.particles[i];
					}
				}

				//if (iteration % 50 == 0 || iteration == numIteration - 1) {
				//System.out.println("Melhor fitness: " + Particle.gBestFitness);
				fw.write(function.name() + ";" + (simulation + 1) + ";" + (iteration + 1) + ";" + ParticleWPSO.gBestFitness + "\n");
				//}
			}//Fim iteraçoes
			fw.close();			
		}//Fim simulações
	}

	@Override
	public PSOResult call(int numParticles, int numIteration, int numDimension, int numSimulation,
			FunctionEnum function) throws Exception {

		PSOType type = PSOType.WPSO;

		PSOResult result = new PSOResult ();
		result.pso = type.name();

		StandardDeviation std_variation = new StandardDeviation();
		double[] std_variation_values = new double[numSimulation];
		DoubleSummaryStatistics average = new DoubleSummaryStatistics();
		
		PSO.numParticles = numParticles;
		PSO.numIteration = numIteration;
		PSO.numDimension = numDimension;
		PSO.numSimulation = numSimulation;
		
		ParticleWPSO.c1 = 2;
		ParticleWPSO.c2 = 2;
		ParticleWPSO.wf = 0.9;
		ParticleWPSO.wi = 0.2;

		//File file;
		//FileWriter fw = null;

		for (int simulation = 0; simulation < numSimulation; simulation ++) {

			//file = new File(type.name() + "_" + function.name() + "_" + "simulation_" + (simulation + 1) + ".txt");
			//fw = new FileWriter(file);
			//fw.write("function;simulation;iteration;value\n");

			ParticleWPSO.particles = new ParticleWPSO[numParticles];
			ParticleWPSO.gBestParticle = null;
			ParticleWPSO.gBestFitness = Double.MAX_VALUE;

			//Inicializa cada particula randomicamente;
			for (int i = 0; i < numParticles; i++) {
				Particle.particles[i] = new ParticleWPSO(numDimension, function, i);
				Particle.particles[i].calcularFitness(function);			
			}

			for (int i = 0; i < numParticles; i++) {				
				if (Particle.particles[i].getFitness() < Particle.gBestFitness) {
					Particle.gBestFitness = Particle.particles[i].getFitness();
					Particle.gBestParticle = Particle.particles[i];
				}
			}

			for (int iteration = 0; iteration < numIteration; iteration++) {
				//Atualiza a velocidade
				for (int i = 0; i < numParticles; i++) {
					try {Particle.particles[i].updateVelocity(iteration);} catch (Exception e) {System.out.println("Erro ao atualizar velocidade: \n");e.printStackTrace();}
				}

				//Atualiza a posição
				for (int i = 0; i < numParticles; i++) {
					Particle.particles[i].updatePosition();
				}

				//Atualiza o fitness
				for (int i = 0; i < numParticles; i++) {
					Particle.particles[i].calcularFitness(function);
				}

				for (int i = 0; i < numParticles; i++) {
					if (Particle.particles[i].getFitness() < Particle.gBestFitness) {
						Particle.gBestFitness = Particle.particles[i].getFitness();
						Particle.gBestParticle = Particle.particles[i];
					}
				}
				
				//if (iteration % 50 == 0 || iteration == numIteration - 1) {
				//System.out.println("Melhor fitness: " + Particle.gBestFitness);
				//fw.write(function.name() + ";" + (simulation + 1) + ";" + (iteration + 1) + ";" + ParticleWPSO.gBestFitness + "\n");
				//}
			}//Fim iteraçoes
			//fw.close();

			double diff = Particle.gBestFitness - Function.getFunctionBias();

			if (diff < result.best) {
				result.best = diff;
			}

			std_variation_values[simulation] = diff;
			average.accept(diff);

		}//Fim simulações

		result.average = average.getAverage();
		result.std_variation = std_variation.evaluate(std_variation_values, result.average);	

		return result;
	}


}