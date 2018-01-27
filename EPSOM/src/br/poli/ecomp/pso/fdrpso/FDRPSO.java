package br.poli.ecomp.pso.fdrpso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.DoubleSummaryStatistics;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import br.poli.ecomp.pso.clpso.ParticleCLPSO;
import br.poli.ecomp.pso.particle.Function;
import br.poli.ecomp.pso.particle.FunctionEnum;
import br.poli.ecomp.pso.particle.PSO;
import br.poli.ecomp.pso.particle.PSOResult;
import br.poli.ecomp.pso.particle.PSOType;
import br.poli.ecomp.pso.particle.Particle;
import br.poli.ecomp.pso.wpso.ParticleWPSO;

public class FDRPSO extends PSO {	

	public static void main(String[] args) throws IOException {

		numParticles = 30;
		numIteration = 10000;
		numDimension = 10;
		numSimulation = 30;
		function = FunctionEnum.Sphere;

		PSOType type = PSOType.FDR_PSO;
		ParticleFDRPSO.particles = new ParticleFDRPSO[numParticles];

		ParticleFDRPSO.wf = 0.9;
		ParticleFDRPSO.wi = 0.2;
		ParticleFDRPSO.c1 = ParticleFDRPSO.c2 = 1;
		ParticleFDRPSO.c3 = 2;

		File file;
		FileWriter fw = null;

		for (int simulation = 0; simulation < numSimulation; simulation ++) {

			file = new File(type.name() + "_" + function.name() + "_" + "simulation_" + (simulation + 1) + ".txt");
			fw = new FileWriter(file);
			fw.write("function;simulation;iteration;value\n");

			ParticleFDRPSO.gBestParticle = null;
			ParticleFDRPSO.gBestFitness = Double.MAX_VALUE;

			//Inicializa cada particula randomicamente;
			for (int i = 0; i < getNumParticles(); i++) {
				ParticleFDRPSO.particles[i] = new ParticleFDRPSO(numDimension, function, i);
				Particle.particles[i].calcularFitness(function);				
			}

			for (int i = 0; i < getNumParticles(); i++) {				
				if (Particle.particles[i].getBestFitness() < Particle.gBestFitness) {
					Particle.gBestFitness = Particle.particles[i].getBestFitness();
					Particle.gBestParticle = Particle.particles[i];
				}
			}

			for (int iteration = 0; iteration < numIteration; iteration++) {

				//Atualiza a velocidade
				for (int i = 0; i < ParticleFDRPSO.particles.length; i++) {
					try {ParticleFDRPSO.particles[i].updateVelocity(iteration);} catch (Exception e) {e.printStackTrace();}					
				}

				//Atualiza a posição
				for (int i = 0; i < ParticleFDRPSO.particles.length; i++) {
					ParticleFDRPSO.particles[i].updatePosition();
				}

				//Atualiza o fitness
				for (int i = 0; i < ParticleFDRPSO.particles.length; i++) {
					ParticleFDRPSO.particles[i].calcularFitness(function);
				}

				for (int i = 0; i < ParticleFDRPSO.particles.length; i++) {
					if (ParticleFDRPSO.particles[i].getBestFitness() < ParticleFDRPSO.gBestFitness) {
						ParticleFDRPSO.gBestFitness = ParticleFDRPSO.particles[i].getBestFitness();
						ParticleFDRPSO.gBestParticle = ParticleFDRPSO.particles[i];
					}
				}

				//if (iteration % 50 == 0 || iteration == numIteration - 1) {
				//System.out.println("Melhor fitness: " + Particle.gBestFitness);
				fw.write(function.name() + ";" + (simulation + 1) + ";" + (iteration + 1) + ";" + ParticleFDRPSO.gBestFitness + "\n");
				//}
			}//Fim iteraçoes
			fw.close();			
		}//Fim simulações
	}

	@Override
	public PSOResult call(int numParticles, int numIteration, int numDimension, int numSimulation,
			FunctionEnum function) throws Exception {

		PSOType type = PSOType.FDR_PSO;

		PSOResult result = new PSOResult ();
		result.pso = type.name();

		StandardDeviation std_variation = new StandardDeviation();
		double[] std_variation_values = new double[numSimulation];
		DoubleSummaryStatistics average = new DoubleSummaryStatistics();

		ParticleFDRPSO.particles = new ParticleFDRPSO[numParticles];

		ParticleFDRPSO.wf = 0.9;
		ParticleFDRPSO.wi = 0.2;
		ParticleFDRPSO.c1 = ParticleFDRPSO.c2 = 1;
		ParticleFDRPSO.c3 = 2;
		
		PSO.numParticles = numParticles;
		PSO.numIteration = numIteration;
		PSO.numDimension = numDimension;
		PSO.numSimulation = numSimulation;
		
		//File file;
		//FileWriter fw = null;

		for (int simulation = 0; simulation < numSimulation; simulation ++) {
			
			File file;
			FileWriter fw = null;
			
			file = new File("FDRPSO" + "_" + function.name() + "_" + "simulation_" + (simulation + 1) + ".txt");
			fw = new FileWriter(file);
			fw.write("function;simulation;iteration;best_fitness\n");
			
			//file = new File(type.name() + "_" + function.name() + "_" + "simulation_" + (simulation + 1) + ".txt");
			//fw = new FileWriter(file);
			//fw.write("function;simulation;iteration;value\n");

			ParticleFDRPSO.gBestParticle = null;
			ParticleFDRPSO.gBestFitness = Double.MAX_VALUE;

			//Inicializa cada particula randomicamente;
			for (int i = 0; i < ParticleWPSO.particles.length; i++) {
				ParticleFDRPSO.particles[i] = new ParticleFDRPSO(numDimension, function, i);
				Particle.particles[i].calcularFitness(function);				
			}

			for (int i = 0; i < getNumParticles(); i++) {				
				if (Particle.particles[i].getBestFitness() < Particle.gBestFitness) {
					Particle.gBestFitness = Particle.particles[i].getBestFitness();
					Particle.gBestParticle = Particle.particles[i];
				}
			}

			for (int iteration = 0; iteration < numIteration; iteration++) {

				//Atualiza a velocidade
				for (int i = 0; i < ParticleFDRPSO.particles.length; i++) {
					try {ParticleFDRPSO.particles[i].updateVelocity(iteration);} catch (Exception e) {e.printStackTrace();}					
				}

				//Atualiza a posição
				for (int i = 0; i < ParticleFDRPSO.particles.length; i++) {
					ParticleFDRPSO.particles[i].updatePosition();
				}

				//Atualiza o fitness
				for (int i = 0; i < ParticleFDRPSO.particles.length; i++) {
					ParticleFDRPSO.particles[i].calcularFitness(function);
				}

				for (int i = 0; i < ParticleFDRPSO.particles.length; i++) {
					if (ParticleFDRPSO.particles[i].getBestFitness() < ParticleFDRPSO.gBestFitness) {
						ParticleFDRPSO.gBestFitness = ParticleFDRPSO.particles[i].getBestFitness();
						ParticleFDRPSO.gBestParticle = ParticleFDRPSO.particles[i];
					}
				}

				if (iteration % 100 == 0 || iteration == numIteration - 1) {
					fw.write(function.name() + ";" + (simulation + 1) + ";" + (iteration + 1) + ";" + ParticleCLPSO.gBestFitness + "\n");
				}
				
				fw.close();
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
