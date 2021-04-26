import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Evaluation } from './evaluation';
import { KhoaService } from '../khoa-service.service';
import { ActivatedRoute } from '@angular/router';

import { MatTreeNestedDataSource } from '@angular/material/tree';
import { NestedTreeControl } from '@angular/cdk/tree';
@Component({
  selector: 'jhi-app-evaluation-criteria',
  templateUrl: './evaluation-criteria.component.html',
})
export class EvaluationCriteriaComponent implements OnInit {
  idTypeReport: Number| null = null;
  evaluationArray!: Evaluation[];
  formEvaluationCriteria!: FormGroup;
  formNewEvaluationCriteria!: FormGroup;
  formTypeReport!: FormGroup;
  treeControl = new NestedTreeControl<Evaluation>(node => node.childEvaluationCriterias);
  dataSource = new MatTreeNestedDataSource<Evaluation>();

  constructor(private route: ActivatedRoute,private formBuilder: FormBuilder, private evaluationService: KhoaService) { }
  hasChild = (_: number, node: Evaluation) => !!node.childEvaluationCriterias && node.childEvaluationCriterias.length > 0;
  ngOnInit(): void {
    
    this.route.data.subscribe(({ idTypeReport }) => (this.idTypeReport = idTypeReport));
    this.getEvaluation(this.idTypeReport);
    this.formEvaluationCriteria = this.structureFormEvaluationCriteria();
    this.formNewEvaluationCriteria = this.structureFormNewEvaluation();
  }
  toggleChildren(employee:any):void {
    employee.visible = !employee.visible;
  }
  structureFormNewEvaluation(): FormGroup {

    return this.formBuilder.group
      (
        {
          id: ['0', Validators.required],
          content: ['', Validators.required],
          maxScore: ['0', Validators.required],
          typeReport: [this.idTypeReport, Validators.required],
          parentId: ['', Validators.required],
        }
      )
  }
  structureFormEvaluationCriteria(): FormGroup {
    return this.formBuilder.group(Evaluation);
  }
  getEvaluation(id: any) :void{
  
    this.evaluationService.getEvaluationByTypeId(id).subscribe(
      data => { this.formEvaluationCriteria = data; this.evaluationArray = data; this.dataSource=data;},
      () => alert("server fail")
    );
 
  }
  deleteEvaluation(id: any):void {

    if (confirm("Do you want delete it?")) {
      this.evaluationService.deleteEvaluation(id).subscribe(
        () => { alert("delete success"); this.getEvaluation(this.idTypeReport); },
        () => alert("delete failed"));
    }
  }
  updateEvaluationCriteria() :void{
    if (confirm("Do you want update all evaluation criteria?")) {
      alert();
    }
  }
  createEvaluation() :void{
    this.evaluationService.createNewEvaluation(this.formNewEvaluationCriteria.value).subscribe(
      
      () => { alert("save success"); this.getEvaluation(this.idTypeReport);},
      () => { alert("save failed");});
  }
  addButton(parentId: Number) :void{
    this.formNewEvaluationCriteria.get('parentId')?.setValue(parentId);
  }
  editButton(id: any,content: any,maxScore: any): void
  {
    this.formEvaluationCriteria.value.id = id;
    this.formEvaluationCriteria.value.content=content;
    this.formEvaluationCriteria.value.maxScore=maxScore;
  }
}
