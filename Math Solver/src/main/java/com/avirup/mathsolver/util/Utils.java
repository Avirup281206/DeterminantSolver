package com.avirup.mathsolver.util;

import com.avirup.mathsolver.BuildConfig;

public final class Utils {

    public static final String info = """
            Math Solver v$version
            Author: Avirup Banerjee
            Version: $version ($version_code)
                                    
            This is a Math Solver made by me. Hope you like it"""
            .replace("$version_code", String.valueOf(BuildConfig.VERSION_CODE))
            .replace("$version", BuildConfig.VERSION_NAME);

    public static final String share_string = """
            Try out this math solver
            https://github.com/Avirup281206/math-solver-mobile/releases/download/v$version"""
            .replace("$version", BuildConfig.VERSION_NAME);

}
