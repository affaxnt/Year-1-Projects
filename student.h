#ifndef STUDENT_H
#define STUDENT_H

#define MAX_NAME_LENGTH 256
#define MAX_STUDENTS 100

typedef struct {
  int id;
  char lastName[MAX_NAME_LENGTH];
  char firstName[MAX_NAME_LENGTH];
  int A1grade;
  int A2grade;
  int A3grade;
  int midTerm;
  int exam;
  float total;
  char letterGrade;
} Student;

typedef struct {
  int minA;
  int minB;
  int minC;
  int minD;
} GradeMapping;

extern GradeMapping gradeMapping; // Declare global GradeMapping

#endif

