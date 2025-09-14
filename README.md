# Hill Cipher File Encryptor

This is a Hill cipher encryption program written in Java. It reads a plaintext file and a key matrix file, applies the Hill cipher algorithm, and outputs the ciphertext. The code is modular, readable, and designed to work with any square key matrix.

## Features

- **Hill Cipher Encryption** - encrypt plaintext using a customizable key matrix.
- **Plaintext preprocessing** - converts input to lowercase and removes non-alphabetic characters.
- **Automatic padding** - pads plaintext with 'x' to fit the matrix dimensions.
- **Formatted output** - displays key matrix, padded plaintext, and ciphertext in an easy-to-read format.
- **Modular design** - separates file reading, padding, formatting, and encryption logic into distinct methods.

## Technical Details

- Developed in Java.
- Uses matrix multiplication and modular arithmetic for encryption.
- Supports any key matrix size specified in the key file.
- CLI-based; takes two arguments: key file and plaintext file.

## How to Run

1. Compile the program:
```bash
javac pa01.java
```
2. Execute the program with a key file and a plaintext file:
```bash
java pa01 kX.txt pX.txt
```
kX.txt - key matrix file (first line: matrix size, following lines: integers of the matrix)
pX.txt - plaintext file (ASCII text)

Example kX.txt:
```bash
3
6 24 1
13 16 10
20 17 15
```
Example Output
```bash
Key matrix:
   6  24   1
  13  16  10
  20  17  15
```
Plaintext: attackatdawn
Resultant ciphertext: lxfopvefrnhr

Created by Charles (Jake) Matthews
