[![Build Status](https://travis-ci.com/AntonisGkortzis/DesignPatterns-SeiP-ProposedSolution.svg?token=G9zDqfcXKyqNsWnKiyZr&branch=master)](https://travis-ci.com/AntonisGkortzis/DesignPatterns-SeiP-ProposedSolution)

# Proposed solution of the 4th SEiP Assingment on Design Patterns

## Introduction
The purpose of this assignment is to refactor a purposefully badly written code base of a system that calculates metrics of a given Java source code file. The main functionalities of the system are:
- __Read a Java source code file__: Read a Java source code file that is stored 1) locally or 2) on the web.
- __Calculate metrics__: Analyze the source code with and retrieve metrics by selecting one of 2 types of analysis, namely, 1) Regular expression and 2) String comparison.
- __Export Metrics__: Write the metrics in a file of type 1) CSV or 2) JSON.

The aforementioned functionalities are implemented in two different modules 1) the Smelly Code Analyzer and 2) the Refactored Code Analyzer.

## 1. A "Smelly" source code analyzer
[Smelly Source Code Analyzer](./SourceCodeAnalyzerSmelly), the purposefully badly written Maven module.

## 2. The Refactored source code analyzer
[Refactored Source Code Analyzer](./sourcecodeanalyzerrefactored), the refactored version of the 1st System that follows the SOLID oop design principles with the use of several Design patterns.

__Important__ : Please check each individual README for design and implementation details.
