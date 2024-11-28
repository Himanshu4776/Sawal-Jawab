import React from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Textarea } from './ui/textarea';

interface QuestionFormProps {
  newQuestion: {
    title: string;
    text: string;
  };
  setNewQuestion: React.Dispatch<React.SetStateAction<{
    title: string;
    text: string;
  }>>;
  addQuestion: () => void;
}

export const QuestionForm: React.FC<QuestionFormProps> = ({
  newQuestion,
  setNewQuestion,
  addQuestion,
}) => {
  return (
    <Card className="bg-white">
      <CardHeader>
        <CardTitle>Ask a Question</CardTitle>
      </CardHeader>
      <CardContent className="space-y-4">
        <Input
          value={newQuestion.title}
          onChange={(e) => setNewQuestion({...newQuestion, title: e.target.value})}
          placeholder="Question Title"
          className="flex-1"
        />
        <Textarea
          value={newQuestion.text}
          onChange={(e) => setNewQuestion({...newQuestion, text: e.target.value})}
          placeholder="Describe your question in detail..."
          className="flex-1"
        />
        <Button onClick={addQuestion}>Ask</Button>
      </CardContent>
    </Card>
  );
};
