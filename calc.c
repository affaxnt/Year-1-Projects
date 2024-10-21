// calc.c
#include "calc.h"
#include <stdio.h>

GradeMapping gradeMapping = {80, 70, 60, 50}; // Default mappings

void calculateGrade(Student *student) {
  float assignmentPercentage = ((student->A1grade + student->A2grade + student->A3grade) / 120.0) * 25.0;
  float midtermPercentage = (student->midTerm / 25.0) * 25.0;
  float examPercentage = (student->exam / 40.0) * 50.0;
  student->total = assignmentPercentage + midtermPercentage + examPercentage;

  if (student->total >= gradeMapping.minA) {
    student->letterGrade = 'A';
  } else if (student->total >= gradeMapping.minB) {
    student->letterGrade = 'B';
  } else if (student->total >= gradeMapping.minC) {
    student->letterGrade = 'C';
  } else if (student->total >= gradeMapping.minD) {
    student->letterGrade = 'D';
  } else {
    student->letterGrade = 'F';
  }
}

void recalculateAllGrades(Student students[], int numStudents) {
  for (int i = 0; i < numStudents; i++) {
    calculateGrade(&students[i]);
  }
}

void updateGradeMappings(Student students[], int numStudents) {
  int newMinA, newMinB, newMinC, newMinD;
  printf("\nCurrent Mapping: " );
  printf("\nA: >= %d", gradeMapping.minA);
  printf("\nB: >= %d", gradeMapping.minB);
  printf("\nC: >= %d", gradeMapping.minC);
  printf("\nD: >= %d", gradeMapping.minD);
  printf("\nF: < %d", gradeMapping.minD, "\n");


  printf("\nEnter new A basline: ");
  scanf("%d", &newMinA);
  printf("Enter new B basline: ");
  scanf("%d", &newMinB);
  printf("Enter new C basline: ");
  scanf("%d", &newMinC);
  printf("Enter new D basline: ");
  scanf("%d", &newMinD);

  if (newMinA > newMinB && newMinB > newMinC && newMinC > newMinD) {
    gradeMapping.minA = newMinA;
    gradeMapping.minB = newMinB;
    gradeMapping.minC = newMinC;
    gradeMapping.minD = newMinD;
    recalculateAllGrades(students, numStudents);
    printf("\nNew Mapping: ");
    printf("\nA: >= %d", newMinA);
    printf("\nB: >= %d", newMinB);
    printf("\nC: >= %d", newMinC);
    printf("\nD: >= %d", newMinD);
    printf("\nF: < %d", newMinD);
    printf("\n");
  } else {
    printf("\nInvalid grade mappings.\n");
  }
}
