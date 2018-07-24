import { Injectable } from '@angular/core';
import * as AWS from 'aws-sdk/global';
import * as S3 from 'aws-sdk/clients/s3';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {
  PROFILE_FOLDER = 'ProfilePicture/';
  POST_FOLDER = 'PostImage/';
  BUCKET_URL = 'http://our-space.s3.amazonaws.com/';


  constructor() { }

  // Upload profile picture to amazon s3
  uploadProfilePicture(file, filename) {
    const bucket = new S3({
      accessKeyId: 'AKIAJYI3MYBPJGVUHN4A',
      secretAccessKey: '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',
      region: 'us-east-1'
    });

    const params = {
      Bucket: 'our-space',
      Key: this.PROFILE_FOLDER + filename,
      Body: file
    };

    bucket.upload(params, function(err, data) {
      if (err) {
        console.log ('there was an error uploading your file: ' + err);
        return false;
      }
      console.log('Successfully uploaded file', data);
      return true;
    });
  }


  // upload post picture to amazon s3
  uploadPostPicture(file, filename) {
    const bucket = new S3({
      accessKeyId: 'AKIAIKU7LDU5RT6NW3FQ',
      secretAccessKey: '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',
      region: 'us-east-1'
    });

    const params = {
      Bucket: 'our-space',
      Key: this.POST_FOLDER + filename,
      acl: 'public-read',
      Body: file
    };

    bucket.upload(params, function(err, data) {
      if (err) {
        console.log ('there was an error uploading your file: ' + err);
        return false;
      }
      console.log('Successfully uploaded file', data);
      return true;
    });
  }
}
