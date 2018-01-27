package br.poli.ecomp.pso.particle;

public enum FunctionEnum {
	
	//Unimodal
	Sphere,														//F1
	Schwefel,													//F2
	Elliptic,													//F3
	Schwefel_noise,												//F4
	Schwefel_global,											//F5
	
	//Multimodal
	Rosenbrock,													//F6
	Griewank,													//F7
	Ackley,														//F8
	Rastrigin,													//F9
	Rastrigin_rotated,											//F10
	Weierstrass_rotated,										//F11
	Schwefel_multimodal,										//F12
	
	//Expanded Functions
	Griewank_plus_Rosenbrock,									//F13
	Scaffer,													//F14
	
	//Hybrid Composition Functions
	Hybrid_composition_function,								//F15
	Rotated_hybrid_composition_function,						//F16
	Rotated_hybrid_composition_function_noise,					//F17
	Rotated_hybrid_composition_function2,						//F18
	Rotated_hybrid_composition_function_global,					//F19
	Rotated_hybrid_composition_function_global_bounds,			//F20
	Rotated_hybrid_composition_function3,						//F21
	Rotated_hybrid_composition_function_high,					//F22
	Non_continuous_rotated_hybrid_composition_function,			//F23
	Rotated_hybrid_composition_function4,						//F24
	Rotated_hybrid_composition_function_bounds;					//F25
	
}
