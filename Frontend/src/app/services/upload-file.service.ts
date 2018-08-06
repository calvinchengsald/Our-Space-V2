import { Injectable } from '@angular/core';
import * as AWS from 'aws-sdk/global';
import * as S3 from 'aws-sdk/clients/s3';
import { env } from '../env/env';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {
  PROFILE_FOLDER = 'ProfilePicture/';
  POST_FOLDER = 'PostImage/';
  BUCKET_URL = 'http://our-space.s3.amazonaws.com/';


  constructor() { }

  // Upload profile picture to amazon s3
  uploadProfilePicture(file, filename, callback) {
    const bucket = new S3({
      accessKeyId: env.AWSs3access,
      secretAccessKey: env.AWSs3secret,
      region: 'us-east-1'
    });

    const params = {
      Bucket: 'our-space',
      Key: this.PROFILE_FOLDER + filename,
      Body: file
    };

    bucket.upload(params, function(err, data) {
      if (err) {
        // console.log ('there was an error uploading your file: ' + err);
        return false;
      }
      // console.log('Successfully uploaded file', data);
      callback();
      return data['Location'];
    });
  }


  // upload post picture to amazon s3
  uploadPostPicture(file, filename, callback) {
    const bucket = new S3({
      accessKeyId: env.AWSs3access,
      secretAccessKey: env.AWSs3secret,
      region: 'us-east-1'
    });

    const params = {
      Bucket: 'our-space',
      Key: this.POST_FOLDER + filename,
      acl: 'public-read',
      Body: file
    };

    const vl = bucket.upload(params, function(err, data) {
      if (err) {
        // console.log ('there was an error uploading your file: ' + err);
        return false;
      }
      // console.log('Successfully uploaded file', data);
      callback();
      return true;
    });
    return vl;
  }
}
