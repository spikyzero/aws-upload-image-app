import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {of} from "rxjs";
import {NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss'],
  standalone: true,
  imports: [
    NgIf,
    FormsModule
  ]
})
export class FileUploadComponent {
  selectedFile: File | null = null;

  constructor(private http: HttpClient) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onUpload() {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      this.http.post<any>('http://localhost:8080/api/v1/files/save', formData)
        .pipe(
          tap(response => {
            console.log('Image uploaded successfully:', response);
            this.selectedFile = null;
            alert('File uploaded succesfully')
          }),
          catchError(error => {
            console.error('Error uploading image:', error);
            return of(null);
          })
        )
        .subscribe();
    } else {
      alert('No file selected!');
      console.error('No file selected!');
    }
  }
}
