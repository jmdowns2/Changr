import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ProjectsComponent } from './projects.component';
import { DetailsComponent } from './details.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule
  ],
  declarations: [ProjectsComponent, DetailsComponent]
})
export class ProjectsModule { }
