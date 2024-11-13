import React from 'react';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';

interface QuestionFormProps {
  newQuestion: string;
  setNewQuestion: React.Dispatch<React.SetStateAction<string>>;
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
      <CardContent>
        <div className="flex gap-2">
          <Input
            value={newQuestion}
            onChange={(e) => setNewQuestion(e.target.value)}
            placeholder="What's your question?"
            className="flex-1"
          />
          <Button onClick={addQuestion}>Ask</Button>
        </div>
      </CardContent>
    </Card>
  );
};
