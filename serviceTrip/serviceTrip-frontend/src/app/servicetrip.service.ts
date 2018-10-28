import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {environment} from "../environments/environment";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class ServicetripService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) { }


  getEvent(cost: string, tag: string) {

    let serviceUrl = environment["urlBase"] + "/event?cost=" + cost + "&tag=" + tag;

    //console.log(serviceUrl);
    //console.log("Response: " + JSON.stringify(this.http.get(serviceUrl)));
    return this.http.get(serviceUrl);
  }

  addEvent(cost: string, tag: string, eventName: string, organization: string, startDate: string, endDate: string, numEnrolled: string, place: string, eventDescription: string){


    let serviceUrl = environment["urlBase"] + "/addevent?cost=" + cost + "&tag=" + tag
    + "&eventName=" + eventName + "&organization=" + organization + "&startDate=" + startDate
      + "&endDate=" + endDate + "&numEnrolled=" + numEnrolled + "&place=" + place + "&eventDescription=" + eventDescription;

    //console.log(serviceUrl);
    console.log("Response: " + JSON.stringify(this.http.get(serviceUrl)));
    return this.http.get(serviceUrl);
  }

}
