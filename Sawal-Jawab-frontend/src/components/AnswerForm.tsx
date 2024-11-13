import React from 'react';
import { Button } from '@/components/ui/button';
import { Textarea } from '@/components/ui/textarea';
import { Send } from 'lucide-react';

interface AnswerFormProps {
  questionId: number;
  addAnswer: (questionId: number, answerText: string) => void;
}

export const AnswerForm: React.FC<AnswerFormProps> = ({
  questionId,
  addAnswer,
}) => {
  return (
    <div className="mt-4">
      <Textarea
        placeholder="Write your answer..."
        id={`answer-${questionId}`}
        className="mb-2"
      />
      <Button
        onClick={() => {
          const answerTextarea = document.getElementById(
            `answer-${questionId}`
          ) as HTMLTextAreaElement;
          const answerText = answerTextarea?.value || '';
          addAnswer(questionId, answerText);
        }}
        className="flex items-center gap-2"
      >
        <Send className="w-4 h-4" />
        Post Answer
      </Button>
    </div>
  );
};
