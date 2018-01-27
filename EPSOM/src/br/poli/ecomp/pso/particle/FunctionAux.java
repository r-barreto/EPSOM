package br.poli.ecomp.pso.particle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FunctionAux {
	
	public static double[] loadOptimum (FunctionEnum function) throws IOException {
		
		File file = null;
		
		if (function == FunctionEnum.Sphere) {	//F01
			file = new File("supportData/sphere_func_data.txt");
		} else if (function == FunctionEnum.Schwefel) { //F02
			file = new File("supportData/schwefel_102_data.txt");
		} else if (function == FunctionEnum.Elliptic) { //F03
			file = new File("supportData/high_cond_elliptic_rot_data.txt");			
		} else if (function == FunctionEnum.Schwefel_noise) { //F04
			file = new File("supportData/schwefel_102_data.txt");
		} else if (function == FunctionEnum.Schwefel_global) { //F05
			file = new File("supportData/schwefel_206_data.txt");
		} else if (function == FunctionEnum.Rosenbrock) { //F06
			file = new File("supportData/rosenbrock_func_data.txt");
		} else if (function == FunctionEnum.Griewank) { //F07
			file = new File("supportData/griewank_func_data.txt");
		} else if (function == FunctionEnum.Ackley) { //F08
			file = new File("supportData/ackley_func_data.txt");			
		} else if (function == FunctionEnum.Rastrigin) { //F09
			file = new File("supportData/rastrigin_func_data.txt");			
		} else if (function == FunctionEnum.Rastrigin_rotated) { //F10
			file = new File("supportData/rastrigin_func_data.txt");
		} else if (function == FunctionEnum.Weierstrass_rotated) { //F11
			file = new File("supportData/weierstrass_data.txt");			
		} else if (function == FunctionEnum.Schwefel_multimodal) { //F12
			/*
			 * Esse arquivo possui apenas matriz a, matriz b e alfa
			 * 
			 */			
			return null;			
		} else if (function == FunctionEnum.Griewank_plus_Rosenbrock) { //F13
			file = new File("supportData/EF8F2_func_data.txt");
		} else if (function == FunctionEnum.Scaffer) { //F14
			file = new File("supportData/E_ScafferF6_func_data.txt");
		} else if (function == FunctionEnum.Hybrid_composition_function) { //F15
			file = new File("supportData/hybrid_func1_data.txt");
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function) { //F16
			file = new File("supportData/hybrid_func1_data.txt");
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_noise) { //F17
			file = new File("supportData/hybrid_func2_data.txt");
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function2) { //F18
			file = new File("supportData/hybrid_func2_data.txt");
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_global) { //F19
			file = new File("supportData/hybrid_func2_data.txt");
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_global_bounds) { //F20
			file = new File("supportData/hybrid_func3_data.txt");
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function3) { //F21
			file = new File("supportData/hybrid_func3_data.txt");
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_high) { //F22
			file = new File("supportData/hybrid_func3_data.txt");
		} else if (function == FunctionEnum.Non_continuous_rotated_hybrid_composition_function) { //F23
			file = new File("supportData/hybrid_func4_data.txt");
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function4) { //F24
			file = new File("supportData/hybrid_func4_data.txt");
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_bounds) { //F25
			file = new File("supportData/hybrid_func4_data.txt");
		}
						
		return loadRowVectorFromFile(file);		
	}
	
	public static double[][] loadMatrix (FunctionEnum function, int qtd) throws IOException {
		
		File file = null;
		
		if (function == FunctionEnum.Sphere) {	//F01
			return new double[0][0];
		} else if (function == FunctionEnum.Schwefel) { //F02
			return new double[0][0];
		} else if (function == FunctionEnum.Elliptic) { //F03
			if (qtd == 2) {
				file = new File("supportData/elliptic_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/elliptic_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/elliptic_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/elliptic_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Schwefel_noise) { //F04
			/*
			 * Este arquivo possui o O e matrix A
			 */
			
			return new double[0][0];
		} else if (function == FunctionEnum.Schwefel_global) { //F05
			/*
			 * Este arquivo possui o O e matrix A
			 */
			
			return new double[0][0];
		} else if (function == FunctionEnum.Rosenbrock) { //F06
			return new double[0][0];
		} else if (function == FunctionEnum.Griewank) { //F07
			if (qtd == 2) {
				file = new File("supportData/griewank_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/griewank_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/griewank_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/griewank_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Ackley) { //F08
			if (qtd == 2) {
				file = new File("supportData/ackley_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/ackley_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/ackley_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/ackley_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rastrigin) { //F09
			if (qtd == 2) {
				file = new File("supportData/rastrigin_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/rastrigin_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/rastrigin_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/rastrigin_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rastrigin_rotated) { //F10
			if (qtd == 2) {
				file = new File("supportData/rastrigin_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/rastrigin_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/rastrigin_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/rastrigin_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Weierstrass_rotated) { //F11
			if (qtd == 2) {
				file = new File("supportData/weierstrass_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/weierstrass_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/weierstrass_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/weierstrass_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Schwefel_multimodal) { //F12
			/*
			 * Esse arquivo possui apenas matriz a, matriz b e alfa
			 */
			return new double[0][0];
		} else if (function == FunctionEnum.Griewank_plus_Rosenbrock) { //F13
			return new double[0][0];
		} else if (function == FunctionEnum.Scaffer) { //F14
			if (qtd == 2) {
				file = new File("supportData/E_ScafferF6_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/E_ScafferF6_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/E_ScafferF6_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/E_ScafferF6_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Hybrid_composition_function) { //F15
			if (qtd == 2) {
				file = new File("supportData/hybrid_func1_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func1_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func1_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func1_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function) { //F16
			if (qtd == 2) {
				file = new File("supportData/hybrid_func1_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func1_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func1_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func1_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_noise) { //F17
			if (qtd == 2) {
				file = new File("supportData/hybrid_func2_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func2_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func2_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func2_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function2) { //F18
			if (qtd == 2) {
				file = new File("supportData/hybrid_func2_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func2_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func2_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func2_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_global) { //F19
			if (qtd == 2) {
				file = new File("supportData/hybrid_func2_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func2_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func2_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func2_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_global_bounds) { //F20
			if (qtd == 2) {
				file = new File("supportData/hybrid_func3_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func3_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func3_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func3_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function3) { //F21
			if (qtd == 2) {
				file = new File("supportData/hybrid_func3_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func3_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func3_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func3_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_high) { //F22
			if (qtd == 2) {
				file = new File("supportData/hybrid_func3_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func3_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func3_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func3_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Non_continuous_rotated_hybrid_composition_function) { //F23
			if (qtd == 2) {
				file = new File("supportData/hybrid_func4_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func4_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func4_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func4_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function4) { //F24
			if (qtd == 2) {
				file = new File("supportData/hybrid_func4_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func4_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func4_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func4_M_D50.txt");
			} else {
				return new double[0][0];
			}
		} else if (function == FunctionEnum.Rotated_hybrid_composition_function_bounds) { //F25
			if (qtd == 2) {
				file = new File("supportData/hybrid_func4_M_D2.txt");
			} else if (qtd == 10) {
				file = new File("supportData/hybrid_func4_M_D10.txt");
			} else if (qtd == 30) {
				file = new File("supportData/hybrid_func4_M_D30.txt");
			} else if (qtd == 50) {
				file = new File("supportData/hybrid_func4_M_D50.txt");
			} else {
				return new double[0][0];
			}
		}
		
		return loadRowMatrixFromFile(file, qtd);
	}
	
	public static double[] loadRowVectorFromFile(File file) {
		
		try {
			BufferedReader brSrc = new BufferedReader(new FileReader(file));
			String stToken;
			StringTokenizer stTokenizer = new StringTokenizer(brSrc.readLine());
			double[] vector = new double[stTokenizer.countTokens()];
			for (int i = 0; stTokenizer.hasMoreTokens(); i++) {
				stToken = stTokenizer.nextToken();
				vector[i] = Double.parseDouble(stToken);
			}
			
			brSrc.close();
			
			return vector;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
	}
	
	public static double[][] loadRowMatrixFromFile(File file, int qtd) {
		
		try {
			BufferedReader brSrc = new BufferedReader(new FileReader(file));
			double[][] vector = new double[qtd][qtd];
			for (int i = 0; i < qtd; i++) {
				
				String stToken;
				StringTokenizer stTokenizer = new StringTokenizer(brSrc.readLine());
				
				for (int j = 0; stTokenizer.hasMoreTokens(); j++) {
					stToken = stTokenizer.nextToken();
					vector[i][j] = Double.parseDouble(stToken);
				}					
			}
			
			brSrc.close();
			
			return vector;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return null;
	}
	
}
