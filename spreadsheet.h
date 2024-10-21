#ifndef SPREADSHEET_H
#define SPREADSHEET_H
#include "student.h"

// Function declarations for spreadsheet-related functions
int countStudentsInSpreadsheet(Student students[]);
void displaySpreadsheet(Student students[], int numStudents);
void printHistogram(Student students[], int numStudents);
void updateLastName(Student students[], int numStudents);
void updateExam(Student students[], int numStudents);

// Declaration for clearScreen function
void clearScreen();

#endif //SPREADSHEET_H
