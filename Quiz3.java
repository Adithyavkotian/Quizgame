
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    
    public class Quiz3 {
        private static final int POINTS_PER_CORRECT_ANSWER = 10;
        
        public static void main(String[] args) {
            System.out.println("Enter your choice:");
        Scanner choice = new Scanner(System.in);
        String userInput = choice.nextLine();
        String choString= userInput+".txt";
        
            List<Question> questions = readQuestionsFromFile(choString);
    
            Scanner scanner = new Scanner(System.in);
            int totalScore = 0;
            List<Question> wrongQuestions = new ArrayList<>(); // Store wrongly answered questions
    
            for (int i = 0; i < questions.size(); i++) {
                System.out.println("Question " + (i + 1) + ": " + questions.get(i).question);
                System.out.print("Enter your answer: ");
                String userAnswer = scanner.nextLine().toLowerCase();
    
                if (userAnswer.equals(questions.get(i).correctAnswer.toLowerCase())) {
                    System.out.println("Correct! +" + POINTS_PER_CORRECT_ANSWER + " points\n");
                    totalScore += POINTS_PER_CORRECT_ANSWER;
                } else {
                    System.out.println("Incorrect! The correct answer is: " + questions.get(i).correctAnswer + "\n");
                    wrongQuestions.add(questions.get(i)); // Add incorrect answer to list
                }
            }
    
            System.out.println("******** Your total score: " + totalScore+"  ********");
    
            // Display wrongly answered questions and their correct answers
            if (!wrongQuestions.isEmpty()) {
                System.out.println("\nYou answered the following questions incorrectly:");
                for (Question question : wrongQuestions) {
                    System.out.println("- " + question.question + "\nCorrect answer: " + question.correctAnswer+"\n");
                }
            } else {
                System.out.println("\nCongratulations! You answered all questions correctly!");
            }
    
            scanner.close();
            choice.close();
        }
    
        // ... rest of the code remains the same
    
    private static List<Question> readQuestionsFromFile(String fileName) {
        List<Question> questions = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                if (parts.length != 2) {
                    System.err.println("Invalid question format: " + line);
                    continue;
                }
                questions.add(new Question(parts[0].trim(), parts[1].trim()));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found: " + fileName);
        }
        return questions;
    }
    
    static class Question {
        String question;
        String correctAnswer;
    
        public Question(String question, String correctAnswer) {
            this.question = question;
            this.correctAnswer = correctAnswer;
        }
    }
    }
    