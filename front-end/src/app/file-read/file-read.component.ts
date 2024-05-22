import {Component} from '@angular/core';
import {NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {HttpClient} from '@angular/common/http';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-file-read',
  standalone: true,
  imports: [
    NgForOf,
    NgOptimizedImage,
    FormsModule,
    NgIf
  ],
  templateUrl: './file-read.component.html',
  styleUrl: './file-read.component.scss'
})
export class FileReadComponent {
  tag: string = '';
  base64Images: string[] = [];
  isEmpty: boolean = false;

  constructor(private http: HttpClient) {
  }

  searchImages() {
    if (this.tag) {
      const url = `http://localhost:8080/api/v1/files/read/all?tag=${this.tag}`;
      this.http.get<string[]>(url)
        .subscribe(base64Images => {
          this.base64Images = base64Images;
          this.isEmpty = this.base64Images.length === 0;
        });
    }
  }

  getBase64Images(base64Image: string): string {
    return `data:image/png;base64,${base64Image}`;
  }

}
