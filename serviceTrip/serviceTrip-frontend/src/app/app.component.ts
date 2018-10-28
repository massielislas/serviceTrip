import { Component } from '@angular/core';
import { ServicetripService } from "./servicetrip.service";
import { environment } from "../environments/environment";
import { Event } from "./event";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public cost: string;
  public tag: string;

  public eventTobeAdded: Event = new Event();

  public events;

  constructor(private servicetripService: ServicetripService){

  }

  getEvents(){
    this.servicetripService.getEvent(this.cost, this.tag).subscribe(
      data => {
        this.events = data;
        console.log(data);
      },
      err => console.error(err),
      () => console.log('done loading events')
    )
  }

  addEvent(){
    console.log(this.eventTobeAdded);
    this.servicetripService.addEvent(this.eventTobeAdded.cost, this.eventTobeAdded.tag,
      this.eventTobeAdded.eventName, this.eventTobeAdded.organization, this.eventTobeAdded.startDate,
      this.eventTobeAdded.endDate, this.eventTobeAdded.numEnrolled, this.eventTobeAdded.place,
      this.eventTobeAdded.eventDescription).subscribe(
      data => {
        console.log(data);
      },
      err => console.error(err),
      () => console.log('done adding event')
    )

  }


}
