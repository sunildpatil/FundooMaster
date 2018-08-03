import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'myfilter',
    pure: false
})
export class MyFilter implements PipeTransform {
    transform(items: any[], obj: string) {
        const filterArr = [];
        items.forEach(item => {
            if (obj == 'trash') {
                if (item.trashed == true) {
                    filterArr.push(item);
                }
            }
            if (obj == 'archive') {
                if (item.trashed == false && item.archived == true) {
                    filterArr.push(item);
                }
            }
            if (obj == 'pin') {
                if (item.trashed == false && item.pinned == true) {
                    filterArr.push(item);
                }
            }
            if (obj == 'other') {
                if (item.trashed==false && item.pinned==false) {
                    filterArr.push(item);
                }
            }
            if(obj == 'reminder'){
                if (item.trashed==false && item.reminderDate!=null) {
                    filterArr.push(item);
                }
            }
        });
        return filterArr;
    }
}