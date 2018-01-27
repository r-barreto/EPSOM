package br.poli.ecomp.pso.particle;

import java.util.Date;

import br.poli.ecomp.cec2005.benchmark;
import br.poli.ecomp.cec2005.test_func;

public class Function {
	
	private static benchmark objBenchmark = new benchmark();
	private static test_func aTestFunc;
	private static FunctionEnum function;
	
	public static int getFunctionIndex (FunctionEnum function) {
		int position = 0;
		for (int i = 0; i < FunctionEnum.values().length; i++) {
			if (FunctionEnum.values()[i].equals(function)) {
				position = i + 1;
				break;
			}
		}
		
		return position;
	}
	
	public static void defineFunction (FunctionEnum function, int dimension) {
		aTestFunc = objBenchmark.testFunctionFactory(getFunctionIndex(function), dimension);
		Function.function = function;
	}
	
	public static double FunctionMax (FunctionEnum function) {

		if (function == FunctionEnum.Sphere) {	//F01
			return 100;
		} else if (function == FunctionEnum.Schwefel) { //F02
			return 100;	
		} else if (function == FunctionEnum.Elliptic) { //F03
			return 100;
		} else if (function == FunctionEnum.Schwefel_noise) { //F04
			return 100;
		} else if (function == FunctionEnum.Schwefel_global) { //F05
			return 100;
		} else if (function == FunctionEnum.Rosenbrock) { //F06
			return 100;
		} else if (function == FunctionEnum.Griewank) { //F07
			return 600;
		} else if (function == FunctionEnum.Ackley) { //F08
			return 32;			
		} else if (function == FunctionEnum.Rastrigin) { //F09
			return 5;			
		} else if (function == FunctionEnum.Rastrigin_rotated) { //F10
			return 5;
		} else if (function == FunctionEnum.Weierstrass_rotated) { //F11
			return 0.5;			
		} else if (function == FunctionEnum.Schwefel_multimodal) { //F12
			return Math.PI;			
		} else if (function == FunctionEnum.Griewank_plus_Rosenbrock) { //F13
			return 5;
		} else if (function == FunctionEnum.Scaffer) { //F14
			return 100;
		} else if (function == FunctionEnum.Hybrid_composition_function) { //F15
			return 5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function) { //F16
			return 5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_noise) { //F17
			return 5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function2) { //F18
			return 5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_global) { //F19
			return 5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_global_bounds) { //F20
			return 5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function3) { //F21
			return 5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_high) { //F22
			return 5;
		} else if (function == FunctionEnum.Non_continuous_rotated_hybrid_composition_function) { //F23
			return 5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function4) { //F24
			return 5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_bounds) { //F25
			return 5;
		}

		return 100;
	}

	public static double FunctionMin (FunctionEnum function) {

		if (function == FunctionEnum.Sphere) {	//F01
			return -100;
		} else if (function == FunctionEnum.Schwefel) { //F02
			return -100;	
		} else if (function == FunctionEnum.Elliptic) { //F03
			return -100;			
		} else if (function == FunctionEnum.Schwefel_noise) { //F04
			return -100;
		} else if (function == FunctionEnum.Schwefel_global) { //F05
			return -100;
		} else if (function == FunctionEnum.Rosenbrock) { //F06
			return -100;
		} else if (function == FunctionEnum.Griewank) { //F07
			return 0;
		} else if (function == FunctionEnum.Ackley) { //F08
			return -32;			
		} else if (function == FunctionEnum.Rastrigin) { //F09
			return -5;			
		} else if (function == FunctionEnum.Rastrigin_rotated) { //F10
			return -5;
		} else if (function == FunctionEnum.Weierstrass_rotated) { //F11
			return -0.5;			
		} else if (function == FunctionEnum.Schwefel_multimodal) { //F12
			return -Math.PI;			
		} else if (function == FunctionEnum.Griewank_plus_Rosenbrock) { //F13
			return -5;
		} else if (function == FunctionEnum.Scaffer) { //F14
			return -100;
		} else if (function == FunctionEnum.Hybrid_composition_function) { //F15
			return -5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function) { //F16
			return -5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_noise) { //F17
			return -5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function2) { //F18
			return -5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_global) { //F19
			return -5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_global_bounds) { //F20
			return -5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function3) { //F21
			return -5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_high) { //F22
			return -5;
		} else if (function == FunctionEnum.Non_continuous_rotated_hybrid_composition_function) { //F23
			return -5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function4) { //F24
			return -5;
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_bounds) { //F25
			return 2;
		}

		return - 100;
	}

	public static void calculateFunction (FunctionEnum function, Particle particle) throws Exception {
		if (Function.function == null || !Function.function.equals(function)) {
			System.out.println(new Date() + " - Definindo uma nova função ( " + function.name() + " ).");
			defineFunction(function, particle.position.length);
		}
		
		particle.fitness = aTestFunc.f(particle.position);
	}
	
	public static double getFunctionBias () {
		if (aTestFunc == null) {
			System.out.println("Defina uma função antes ...");
			System.exit(1);
		}
		
		return aTestFunc.bias();
	}
	
}
