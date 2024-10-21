// spreadsheet.c
#include "spreadsheet.h"
#include "ordering.h"
#include "calc.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int countStudentsInFile(const char *filename);
void displaySpreadsheet(Student students[], int numStudents) {
  printf("COMP 348 GRADE SHEET\n");
  printf("ID     \tLAST       \tFIRST      \tA1  A2  A3    Midterm    Exam     Total    Grade\n");
  printf("--     \t----       \t-----      \t--  --  --    -------    ----     -----    -----\n");
  for (int i = 0; i < numStudents; i++) {
    printf("%-6d \t%-10s \t%-10s \t%2d  %2d  %2d    %3d     %5d    %8.2f    %2c\n",
           students[i].id,
           students[i].lastName,
           students[i].firstName,
           students[i].A1grade,
           students[i].A2grade,
           students[i].A3grade,
           students[i].midTerm,
           students[i].exam,
           students[i].total,
           students[i].letterGrade);
  }
}

// Display a histogram based on students' letter grades
void printHistogram(Student students[], int numStudents) {
  printf("\nCOMP 348 Grade Distribution:\n");

  int numA = 0, numB = 0, numC = 0, numD = 0, numF = 0;

  // Count the number of students for each letter grade
  for (int i = 0; i < numStudents; i++) {
    switch (students[i].letterGrade) {
    case 'A': numA++; break;
    case 'B': numB++; break;
    case 'C': numC++; break;
    case 'D': numD++; break;
    case 'F': numF++; break;
    }
  }

  // Print the histogram
  printf("A: ");
  for (int j = 0; j < numA; j++) {
    printf("*");
  }
  printf("\nB: ");
  for (int j = 0; j < numB; j++) {
    printf("*");
  }
  printf("\nC: ");
  for (int j = 0; j < numC; j++) {
    printf("*");
  }
  printf("\nD: ");
  for (int j = 0; j < numD; j++) {
    printf("*");
  }
  printf("\nF: ");
  for (int j = 0; j < numF; j++) {
    printf("*");
  }
  printf("\n");
}

void updateLastName(Student students[], int numStudents) {
  int studentId;
  char newName[MAX_NAME_LENGTH];
  int found = 0;
  displaySpreadsheet(students, numStudents);
  printf("\nEnter Student ID: ");
  scanf("%d", &studentId);
  printf("\nEnter updated Last name: ");
  getchar();
  scanf("%[^\n]", newName);

  for (int i = 0; i < numStudents; i++) {
    if (students[i].id == studentId) {
      strcpy(students[i].lastName, newName);
      found = 1;
      printf("\nLast name updated.\n");
      break;
    }
  }

  if (!found) {
    printf("\nStudent ID not found.\n");
  }
}

// Update the exam grade for a specific student
void updateExam(Student students[], int numStudents) {
  int studentId;
  int newGrade;
  int found = 0;
displaySpreadsheet(students, numStudents);
  // Get the student ID from the user
  printf("\nEnter Student ID: ");
  scanf("%d", &studentId);

  // Find the student with the given ID
  for (int i = 0; i < numStudents; i++) {
    if (students[i].id == studentId) {
      // Get the new grade from the user
      printf("\nEnter the new exam grade: ");
      scanf("%d", &newGrade);

      // Validate the new grade
      if (newGrade < 0 || newGrade > 100) {
        printf("\nInvalid grade. Please enter a value between 0 and 100.\n");
        return;
      }

      // Update the exam grade
      students[i].exam = newGrade;

      // Recalculate the total grade and letter grade for the student
      calculateGrade(&students[i]);

      found = 1;
      printf("\nExam grade updated successfully.\n");
      break;
    }
  }

  // If the student was not found, notify the user
  if (!found) {
    printf("\nStudent ID not found.\n");
  }
}

void clearScreen() {
#ifdef _WIN32
  system("cls");  // For Windows
#else
  system("clear");  // For Linux and macOS
#endif // End of #ifdef _WIN32
}
