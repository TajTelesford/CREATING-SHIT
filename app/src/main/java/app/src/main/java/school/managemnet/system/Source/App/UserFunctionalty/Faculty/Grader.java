package app.src.main.java.school.managemnet.system.Source.App.UserFunctionalty.Faculty;

import java.util.ArrayList;
import java.util.List;

public class Grader {
    public static int Grade(String submission, String correct_answers)
    {
        if(submission.equals("")) return 0;
        List<Character> student_answer = TokenizeAnswers(submission);
        List<Character> answer = TokenizeAnswers(correct_answers);

        System.out.println("Student: " + student_answer);
        System.out.println("Correct: " + correct_answers);

        return (int)GradeAnswers(student_answer, answer);
    }

    private static double GradeAnswers(List<Character> student_answer, List<Character> answer) 
    {
        int correct = 0;
        for(int i = 0; i < student_answer.size(); i++)
        {
            if(student_answer.get(i) == answer.get(i)) 
            {
                System.out.println(student_answer.get(i));
                System.out.println(answer.get(i));
                correct++;
            }
        }
        double res = ((double) correct / answer.size()) * 100;
        return res;
    }

    private static List<Character> TokenizeAnswers(String submission)
    {
        List<Character> list = new ArrayList<>();
        for(char c : submission.toCharArray())
            if(Character.isLetter(c)) list.add(c);
        return list;
    }
}
