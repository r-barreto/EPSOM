package br.poli.ecomp.pso.lips;

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

public class LIPS extends PSO {

	public static void main(String[] args) throws IOException {

		numParticles = 30;
		numIteration = 10000;
		numDimension = 10;
		numSimulation = 30;
		function = FunctionEnum.Sphere;

		PSOType type = PSOType.LIPS;
		ParticleLIPS.particles = new ParticleLIPS[numParticles];

		ParticleLIPS.nsize = 3;
		ParticleLIPS.X = 0.729;

		File file;
		FileWriter fw = null;

		for (int simulation = 0; simulation < numSimulation; simulation ++) {

			file = new File(type.name() + "_" + function.name() + "_" + "simulation_" + (simulation + 1) + ".txt");
			fw = new FileWriter(file);
			fw.write("function;simulation;iteration;value\n");

			ParticleLIPS.gBestParticle = null;
			ParticleLIPS.gBestFitness = Double.MAX_VALUE;

			//Inicializa cada particula randomicamente;
			for (int i = 0; i < ParticleLIPS.particles.length; i++) {
				ParticleLIPS.particles[i] = new ParticleLIPS(numDimension, function, i);

				if (ParticleLIPS.particles[i].getBestFitness() < ParticleLIPS.gBestFitness) {
					ParticleLIPS.gBestFitness = ParticleLIPS.particles[i].getBestFitness();
					ParticleLIPS.gBestParticle = ParticleLIPS.particles[i];
				}
			}

			for (int iteration = 0; iteration < numIteration; iteration++) {

				//Atualiza a velocidade
				for (int i = 0; i < ParticleLIPS.particles.length; i++) {
					try {ParticleLIPS.particles[i].updateVelocity(iteration);} catch (Exception e) {e.printStackTrace();}					
				}

				//Atualiza a posição
				for (int i = 0; i < ParticleLIPS.particles.length; i++) {
					ParticleLIPS.particles[i].updatePosition();
				}

				//Atualiza o fitness
				for (int i = 0; i < ParticleLIPS.particles.length; i++) {
					ParticleLIPS.particles[i].calcularFitness(function);
				}

				for (int i = 0; i < ParticleLIPS.particles.length; i++) {
					if (ParticleLIPS.particles[i].getBestFitness() < ParticleLIPS.gBestFitness) {
						ParticleLIPS.gBestFitness = ParticleLIPS.particles[i].getBestFitness();
						ParticleLIPS.gBestParticle = ParticleLIPS.particles[i];
					}
				}

				//if (iteration % 50 == 0 || iteration == numIteration - 1) {
				//System.out.println("Melhor fitness: " + Particle.gBestFitness);
				fw.write(function.name() + ";" + (simulation + 1) + ";" + (iteration + 1) + ";" + ParticleLIPS.gBestFitness + "\n");
				//}
			}//Fim iteraçoes
			fw.close();
		}//Fim simulações
	}

	@Override
	public PSOResult call(int numParticles, int numIteration, int numDimension, int numSimulation,
			FunctionEnum function) throws Exception {

		PSOType type = PSOType.LIPS;

		PSOResult result = new PSOResult ();
		result.pso = type.name();

		StandardDeviation std_variation = new StandardDeviation();
		double[] std_variation_values = new double[numSimulation];
		DoubleSummaryStatistics average = new DoubleSummaryStatistics();

		ParticleLIPS.particles = new ParticleLIPS[numParticles];

		ParticleLIPS.nsize = 3;
		ParticleLIPS.c = 2;
		ParticleLIPS.X = 0.7298;
		
		PSO.numParticles = numParticles;
		PSO.numIteration = numIteration;
		PSO.numDimension = numDimension;
		PSO.numSimulation = numSimulation;
		
		//File file;
		//FileWriter fw = null;

		for (int simulation = 0; simulation < numSimulation; simulation ++) {

			File file;
			FileWriter fw = null;
			
			file = new File(type.name() + "_" + function.name() + "_" + "simulation_" + (simulation + 1) + ".txt");
			fw = new FileWriter(file);
			fw.write("function;simulation;iteration;best_fitness\n");

			ParticleLIPS.gBestParticle = null;
			ParticleLIPS.gBestFitness = Double.MAX_VALUE;
			
			//ParticleLIPS.nsize = 2;

			//Inicializa cada particula randomicamente;
			for (int i = 0; i < getNumParticles(); i++) {
				ParticleLIPS.particles[i] = new ParticleLIPS(numDimension, function, i);
				Particle.particles[i].calcularFitness(function);				
			}

			for (int i = 0; i < getNumParticles(); i++) {				
				if (Particle.particles[i].getFitness() < Particle.gBestFitness) {
					Particle.gBestFitness = Particle.particles[i].getFitness();
					Particle.gBestParticle = Particle.particles[i];
				}
			}

			for (int iteration = 0; iteration < numIteration; iteration++) {

				//Atualiza a velocidade
				for (int i = 0; i < ParticleLIPS.particles.length; i++) {
					try {ParticleLIPS.particles[i].updateVelocity(iteration);} catch (Exception e) {e.printStackTrace();}					
				}

				//Atualiza a posição
				for (int i = 0; i < ParticleLIPS.particles.length; i++) {
					ParticleLIPS.particles[i].updatePosition();
				}

				//Atualiza o fitness
				for (int i = 0; i < ParticleLIPS.particles.length; i++) {
					ParticleLIPS.particles[i].calcularFitness(function);
				}

				for (int i = 0; i < ParticleLIPS.particles.length; i++) {
					if (ParticleLIPS.particles[i].getFitness() < ParticleLIPS.gBestFitness) {
						ParticleLIPS.gBestFitness = ParticleLIPS.particles[i].getFitness();
						ParticleLIPS.gBestParticle = ParticleLIPS.particles[i];
					}
				}
				
				/*
				if (iteration/(3 * ParticleLIPS.nsize) > numIteration/4) {
					ParticleLIPS.nsize ++;
				}
				*/
				
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
