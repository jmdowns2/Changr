import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router'
import { LoginComponent} from "./login/login.component"
import { ProjectsComponent} from "./projects/projects.component"
import { DetailsComponent } from './projects/details.component';

const routes: Routes = [
  { path: 'id_token', component: LoginComponent },
  { path: 'projects', component: ProjectsComponent },
  { path: 'projects/:id', component: DetailsComponent }

//  { path: 'heroes', component: HeroesComponent }
];


@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes, { useHash: true })
  ],
  exports: [
    RouterModule
  ],
  declarations: []
})
export class AppRoutingModule { }
