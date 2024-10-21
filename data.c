#include "data.h"
#include "calc.h"
#include "spreadsheet.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int loadStudents(Student **students, const char *filePath) {
  // Count the number of students
  int numStudents = countStudentsInFile(filePath);
  if (numStudents == 0) {
    printf("No students found in file or error reading file.\n");
    return 0;
  }

  // Dynamically allocate memory for the students array
  *students = (Student *)malloc(numStudents * sizeof(Student));
  if (*students == NULL) {
    printf("Memory allocation failed!\n");
    return 0;
  }

  // Open the file and load student data
  FILE *fptr = fopen("students.txt", "r");  // Open Student file in read mode
  if (fptr == NULL) {
    printf("File not found\n");
    free(*students);  // Free allocated memory in case of error
    return 0;
  }

  char row_buffer[256];
  int studentIndex = 0;

  while (fgets(row_buffer, sizeof(row_buffer), fptr) != NULL) {
    sscanf(row_buffer, "%d|%[^|]|%[^|]|%d|%d|%d|%d|%d",
           &(*students)[studentIndex].id,
           (*students)[studentIndex].lastName,
           (*students)[studentIndex].firstName,
           &(*students)[studentIndex].A1grade,
           &(*students)[studentIndex].A2grade,
           &(*students)[studentIndex].A3grade,
           &(*students)[studentIndex].midTerm,
           &(*students)[studentIndex].exam);

    // Calculate the grade after loading the data
    calculateGrade(&(*students)[studentIndex]);

    studentIndex++;
  }

  fclose(fptr);
  return numStudents;
}


void removeStudent(Student students[], int *numStudents) {
  int studentId;
  int found = 0;
  displaySpreadsheet(students, *numStudents);
  printf("\nEnter Student ID to remove: ");
  scanf("%d", &studentId);

  for (int i = 0; i < *numStudents; i++) {
    if (students[i].id == studentId) {
      for (int j = i; j < *numStudents - 1; j++) {
        students[j] = students[j + 1];
      }

      (*numStudents)--;
      found = 1;
      printf("\nStudent removed successfully.\n");
      break;
    }
  }

  if (!found) {
    printf("\nStudent ID not found.\n");
  }
}
