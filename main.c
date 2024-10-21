#include "calc.h"
#include "data.h"
#include "ordering.h"
#include "spreadsheet.h"
#include "student.h"
#include <stdio.h>
#include <stdlib.h>

int main() {
    int choice;
    char proceed;
    Student *students= NULL;  // Array to store student data
    int numStudents = 0;   // Number of students

    // Load students from file
    numStudents = loadStudents(students, "students.txt");
    if (numStudents == 0) {
        return 1;  // Exit if no students were loaded
    }

    // Main loop
    do {
        // Clear the screen before displaying the menu
        clearScreen();

        // Menu options
        printf("Spreadsheet Menu \n");
        printf("---------------- \n");
        printf("1. Display Spreadsheet \n");
        printf("2. Display Histogram \n");
        printf("3. Set sort column \n");
        printf("4. Update Last Name \n");
        printf("5. Update Exam Grade \n");
        printf("6. Update Grade Mapping \n");
        printf("7. Delete Student \n");
        printf("8. Exit \n");

        // Get user's choice
        printf("\nSelection: ");
        scanf("%d", &choice);

        // Handle the user's selection
        switch (choice) {
            case 1:
                qsort(students, numStudents, sizeof(Student), compareByID);
                displaySpreadsheet(students, numStudents);  // Display the sorted spreadsheet
                break;
            case 2:
                printHistogram(students, numStudents);
                break;
            case 3:
                printf("Column Options\n");
                printf("---------------\n");
                printf("1. Student ID \n");
                printf("2. Last Name  \n");
                printf("3. Exam \n");
                printf("4. Total \n");

                int sortChoice;
                printf("\nSort Column: ");
                scanf("%d", &sortChoice);

                switch (sortChoice) {
                    case 1:
                        qsort(students, numStudents, sizeof(Student), compareByID);
                        break;
                    case 2:
                        qsort(students, numStudents, sizeof(Student), compareByName);
                        break;
                    case 3:
                        qsort(students, numStudents, sizeof(Student), compareByExam);
                        break;
                    case 4:
                        qsort(students, numStudents, sizeof(Student), compareByTotal);
                        break;
                    default:
                        printf("Invalid selection. Please try again.\n");
                        continue;  // Skip asking to continue and show menu again
                }
                displaySpreadsheet(students, numStudents);
                break;
            case 4:
                updateLastName(students, numStudents);
                break;
            case 5:
                updateExam(students, numStudents);
                break;
            case 6:
                updateGradeMappings(students, numStudents);
                break;
            case 7:
                removeStudent(students, &numStudents);
                break;
            case 8:
                printf("Goodbye and thanks for using our spreadsheet app\n");
                free(students);  // Free dynamically allocated memory before exiting
                return 0;
            default:
                printf("Invalid selection. Please try again.\n");
                continue;
        }

        // Ask user if they want to continue
        printf("\nPress 'c' or 'C' to continue: ");
        getchar(); // Consume leftover newline character
        scanf("%c", &proceed);
    } while (proceed == 'c' || proceed == 'C');

    return 0;
}
