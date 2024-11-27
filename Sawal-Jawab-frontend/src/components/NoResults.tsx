import { CircleAlert } from "lucide-react";

export function NoResults() {
    return (
        <div className="flex items-center justify-center">
            <div className="py-4">
                <CircleAlert size={240} />
                <div className="-pl-20">
                <p>No Results found. Please try some other search</p>
                </div>
            </div>
        </div>
    );
}