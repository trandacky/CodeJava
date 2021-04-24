export class Evaluation {
    id?: Number;
    createDate?: Date;
    updateDate?: Date;
    content?: String;
    maxScore?: number;
    enable?: boolean;
    childEvaluationCriterias?: Evaluation[];
}
