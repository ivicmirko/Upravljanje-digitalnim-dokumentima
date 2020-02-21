export class MagazineDTO {
    id:Number;
    name:String;
    issn:String;
    scienceAreas:String[];
    membershipType:String;
    username:String;

    editors=[];
    reviewers=[];

    constructor(id:number,name:string,issn:string, scienceAreas:string[],
        membershipType:string, username:string, editors:string[],
        reviewers:string[]){
        this.id=id;
        this.name=name;
        this.issn=issn;
        this.membershipType=membershipType;
        this.scienceAreas=scienceAreas;
        this.username=username;
        this.reviewers=reviewers;
        this.editors=editors;
    }
    
}
