package br.poli.ecomp.pso.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

import br.poli.ecomp.pso.clpso.CLPSO;
import br.poli.ecomp.pso.epso.EPSO;
import br.poli.ecomp.pso.epsom.EPSOM;
import br.poli.ecomp.pso.fdrpso.FDRPSO;
import br.poli.ecomp.pso.hpsotvac.HPSOTVAC;
import br.poli.ecomp.pso.lips.LIPS;
import br.poli.ecomp.pso.particle.FunctionEnum;
import br.poli.ecomp.pso.particle.PSOResult;
import br.poli.ecomp.pso.wpso.WPSO;

public class Main {

	private static final DecimalFormat scientificFormatter = new DecimalFormat("0.0000E00");

	@SuppressWarnings("unchecked")
	private static void writePSOResult (FileWriter fw, FunctionEnum function, ArrayList<PSOResult> result, Comparator<PSOResult> comp) throws IOException {

		ArrayList<PSOResult> resultadoOrdenado = (ArrayList<PSOResult>) result.clone();

		resultadoOrdenado.sort(comp);

		fw.write(function.name());

		for (int i = 0; i < result.size(); i++) {
			fw.write(";" + result.get(i).pso + " - Rank: " + (resultadoOrdenado.indexOf(result.get(i)) + 1) + " - Best: " + scientificFormatter.format(result.get(i).best) + " - Average: " + scientificFormatter.format(result.get(i).average) + " - Std Variation: " + scientificFormatter.format(result.get(i).std_variation));
			System.out.println(function.name() + ";" + result.get(i).pso + " - Rank: " + (resultadoOrdenado.indexOf(result.get(i)) + 1) + " - Best: " + scientificFormatter.format(result.get(i).best) + " - Average: " + scientificFormatter.format(result.get(i).average) + " - Std Variation: " + scientificFormatter.format(result.get(i).std_variation) + "\n");
		}

		fw.write("\n");

	}

	public static void main(String[] args) throws IOException {

		File fileBest, fileAverage, fileStdVariation;
		FileWriter fwBest, fwAverage, fwStdVariation;

		fileBest = new File("resultado - best.csv");
		fileAverage = new File("resultado - average.csv");
		fileStdVariation = new File("resultado - std_variation.csv");
		fwBest = new FileWriter(fileBest);
		fwAverage = new FileWriter(fileAverage);
		fwStdVariation = new FileWriter(fileStdVariation);

		fwBest.write("function;EPSOM;EPSO;WPSO;LIPS;CLPSO;HPSOTVAC;FDRPSO\n");
		fwAverage.write("function;EPSOM;EPSO;WPSO;LIPS;CLPSO;HPSOTVAC;FDRPSO\n");
		fwStdVariation.write("function;EPSOM;EPSO;WPSO;LIPS;CLPSO;HPSOTVAC;FDRPSO\n");

		try {
			
			FunctionEnum[] functions = {FunctionEnum.Sphere,
					FunctionEnum.Schwefel,
					FunctionEnum.Elliptic,
					FunctionEnum.Schwefel_noise,
					FunctionEnum.Schwefel_global,
					FunctionEnum.Rosenbrock,
					FunctionEnum.Griewank,
					FunctionEnum.Ackley,
					FunctionEnum.Rastrigin,
					FunctionEnum.Rastrigin_rotated,
					FunctionEnum.Weierstrass_rotated,
					FunctionEnum.Schwefel_multimodal,
					FunctionEnum.Griewank_plus_Rosenbrock,
					FunctionEnum.Scaffer,
					FunctionEnum.Hybrid_composition_function,
					FunctionEnum.Rotated_hybrid_composition_function,
					FunctionEnum.Rotated_hybrid_composition_function_noise,
					FunctionEnum.Rotated_hybrid_composition_function2,
					FunctionEnum.Rotated_hybrid_composition_function_global,
					FunctionEnum.Rotated_hybrid_composition_function_global_bounds,
					FunctionEnum.Rotated_hybrid_composition_function3,
					FunctionEnum.Rotated_hybrid_composition_function_high,
					FunctionEnum.Non_continuous_rotated_hybrid_composition_function,
					FunctionEnum.Rotated_hybrid_composition_function4,
					FunctionEnum.Rotated_hybrid_composition_function_bounds};
			
			/*
			FunctionEnum[] functions = {FunctionEnum.Sphere,
					FunctionEnum.Schwefel,
					FunctionEnum.Elliptic,
					FunctionEnum.Schwefel_noise,
					FunctionEnum.Schwefel_global,
					FunctionEnum.Rosenbrock,
					FunctionEnum.Griewank,
					FunctionEnum.Ackley,
					FunctionEnum.Rastrigin,
					FunctionEnum.Rastrigin_rotated,
					FunctionEnum.Weierstrass_rotated,
					FunctionEnum.Schwefel_multimodal,
					FunctionEnum.Griewank_plus_Rosenbrock,
					FunctionEnum.Scaffer};
			*/

			//FunctionEnum[] functions = {FunctionEnum.Sphere, FunctionEnum.Schwefel, FunctionEnum.Elliptic};

			int numParticle, numIteration, numDimension, numSimulation;
			numParticle = 20;
			numIteration = 10000;
			numDimension = 10;
			numSimulation = 30;

			for (FunctionEnum f : functions) {

				ArrayList<PSOResult> resultados = new ArrayList<PSOResult> ();

				/*
				LIPS lips = new LIPS();
				PSOResult resultLips = lips.call(numParticle, numIteration, numDimension, numSimulation, f);
				resultados.add(resultLips);

				HPSOTVAC hpsotvac = new HPSOTVAC();
				PSOResult resultHpsoTvac = hpsotvac.call(numParticle, numIteration, numDimension, numSimulation, f);				
				resultados.add(resultHpsoTvac);
				 */


				EPSOM epsom = new EPSOM();
				PSOResult resultEpsom = epsom.call(numParticle, numIteration, numDimension, numSimulation, f);

				resultados.add(resultEpsom);

				EPSO epso = new EPSO();
				PSOResult resultEpso = epso.call(numParticle, numIteration, numDimension, numSimulation, f);

				resultados.add(resultEpso);

				WPSO wpso = new WPSO();
				PSOResult resultWpso = wpso.call(numParticle, numIteration, numDimension, numSimulation, f);

				resultados.add(resultWpso);

				LIPS lips = new LIPS();
				PSOResult resultLips = lips.call(numParticle, numIteration, numDimension, numSimulation, f);

				resultados.add(resultLips);

				CLPSO clpso = new CLPSO();
				PSOResult resultClpso = clpso.call(numParticle, numIteration, numDimension, numSimulation, f);

				resultados.add(resultClpso);

				HPSOTVAC hpsotvac = new HPSOTVAC();
				PSOResult resultHpsoTvac = hpsotvac.call(numParticle, numIteration, numDimension, numSimulation, f);

				resultados.add(resultHpsoTvac);

				FDRPSO fdrpso = new FDRPSO();
				PSOResult resultFdrPso = fdrpso.call(numParticle, numIteration, numDimension, numSimulation, f);

				resultados.add(resultFdrPso);

				writePSOResult(fwAverage, f, resultados, new Comparator<PSOResult>() {
					@Override
					public int compare(PSOResult o1, PSOResult o2) {
						return Double.compare(o1.average, o2.average);
					}
				});
				/*
				writePSOResult(fwBest, f, resultados, new Comparator<PSOResult>() {
					@Override
					public int compare(PSOResult o1, PSOResult o2) {
						return Double.compare(o1.best, o2.best);
					}
				});				
				writePSOResult(fwStdVariation, f, resultados, new Comparator<PSOResult>() {
					@Override
					public int compare(PSOResult o1, PSOResult o2) {
						return Double.compare(o1.std_variation, o2.std_variation);
					}
				});
				 */
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fwBest.close();
			fwAverage.close();
			fwStdVariation.close();
		}


	}	
}