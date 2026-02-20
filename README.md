
---

# PLSSC — Longest Common Subsequence (LCS)

Java implementation of the **Longest Common Subsequence (LCS)** problem (Plus Longue Sous-Séquence Commune).

This program computes the longest common subsequence between two strings using:

* A naïve recursive approach
* A dynamic programming approach

It also measures execution time for performance comparison.

---

## Overview

Given two strings ( S1 ) and ( S2 ), the program computes their longest common subsequence (PLSSC).

Two methods are implemented:

1. **Naïve recursive method (-n)**

    * No memoization
    * Exponential complexity
    * Includes pruning using an upper bound

2. **Dynamic programming method (-p)**

    * Uses a 2D table
    * Time complexity: O(n × m)
    * Efficient and scalable

3. **Both methods (-a)**

    * Runs both algorithms
    * Compares execution time

---

## Input Format

The program expects a file containing:

```
FirstString
SecondString
```

Each string must be on its own line.

---

## Compilation

```bash
javac RecherchePLSSC.java
```

---

## Execution

```bash
java RecherchePLSSC -n input.txt
```

Options:

* `-n` → naïve recursive method
* `-p` → dynamic programming method
* `-a` → both methods

Example:

```bash
java RecherchePLSSC -a exemple.txt
```

---

## Output

The program prints:

* The computed LCS
* The lengths of the two input strings
* The execution time in seconds

Example output:

```
PLSSC_n: ABCD
Time_n: 10    12    0.0032
PLSSC_p: ABCD
Time_p: 10    12    0.0001
```

---

## Educational Objectives

* Understand recursive problem solving
* Compare brute-force vs dynamic programming
* Analyze algorithmic complexity
* Measure performance experimentally

---
