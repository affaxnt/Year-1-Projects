#ifndef DATA_H
#define DATA_H

#include "student.h"

// Function prototypes for data management
int countStudentsInFile(const char *filename);
int loadStudents(Student **students, const char *filePath);
void removeStudent(Student students[], int *numStudents);

#endif
