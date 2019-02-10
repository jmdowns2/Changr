import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ProjectsComponent } from './projects.component';
import { DetailsComponent } from './details.component';
import { JobComponent } from './job.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule
  ],
  declarations: [ProjectsComponent, DetailsComponent, JobComponent]
})
export class ProjectsModule { }
