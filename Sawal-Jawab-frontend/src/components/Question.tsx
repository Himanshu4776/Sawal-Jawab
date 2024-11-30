import React from "react";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Link, MessageCircle, ThumbsUp, User } from "lucide-react";
import { Answer } from "./Answer";
import { AnswerForm } from "./AnswerForm";
import { badgeVariants } from "@/components/ui/badge";

interface AnswerType {
  id: number;
  author: string;
  timestamp: string;
  text: string;
  votes: number;
}

interface QuestionType {
  id: number;
  title: string;
  author: string;
  timestamp: string;
  text: string;
  votes: number;
  answers: AnswerType[];
}

interface QuestionProps {
  question: QuestionType;
  handleVote: (questionId: number, answerId?: number) => void;
  showAnswerInput: Record<number, boolean>;
  setShowAnswerInput: React.Dispatch<
    React.SetStateAction<Record<number, boolean>>
  >;
  addAnswer: (questionId: number, answerText: string) => void;
}

export const Question: React.FC<QuestionProps> = ({
  question,
  handleVote,
  showAnswerInput,
  setShowAnswerInput,
  addAnswer,
}) => {
  return (
    <Card className="bg-white">
      <CardContent className="pt-6">
        <div className="flex items-start justify-between">
          <div className="flex-1">
            <div className="flex items-center gap-2 mb-2">
              <User className="w-4 h-4" />
              <span className="text-sm text-gray-500">{question.author}</span>
              <span className="text-sm text-gray-500">
                • {question.timestamp}
              </span>
              {/* <div className="px-8">
                <Link className={badgeVariants({ variant: 'outline' })}>
                  Badge fot
                </Link>
              </div> */}
            </div>
            <div>
              <h2 className="text-2xl font-bold mb-2">{question.title}</h2>
              <p className="mb-4">{question.text}</p>
            </div>
            <div className="flex items-center gap-4 text-sm text-gray-500">
              <Button
                variant="ghost"
                size="sm"
                onClick={() => handleVote(question.id)}
                className="flex items-center gap-1"
              >
                <ThumbsUp className="w-4 h-4" />
                {question.votes}
              </Button>
              <Button
                variant="ghost"
                size="sm"
                onClick={() =>
                  setShowAnswerInput({
                    ...showAnswerInput,
                    [question.id]: !showAnswerInput[question.id],
                  })
                }
                className="flex items-center gap-1"
              >
                <MessageCircle className="w-4 h-4" />
                {question.answers?.length} Answers
              </Button>
            </div>
          </div>
        </div>

        {showAnswerInput[question.id] && (
          <AnswerForm questionId={question.id} addAnswer={addAnswer} />
        )}

        <div className="ml-8 space-y-4">
          {" "}
          {/* Add margin-left for indentation */}
          {question.answers?.map((answer) => (
            <Answer
              key={answer.id}
              answer={answer}
              handleVote={handleVote}
              questionId={question.id}
            />
          ))}
        </div>
      </CardContent>
    </Card>
  );
};
