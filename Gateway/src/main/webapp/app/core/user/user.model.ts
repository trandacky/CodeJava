import { AccountDetailDTO } from 'app/core/user/AccountDetail';
export interface IUser {
  uuid?: any;
  username?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  activated?: boolean;
  langKey?: string;
  authorities?: string[];
  createdBy?: string;
  createdDate?: Date;
  updatedBy?: string;
  updateDate?: Date;
  password?: string;
}

export class User implements IUser {
  constructor(
    public uuid?: any,
    public username?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public activated?: boolean,
    public langKey?: string,
    public authorities?: string[],
    public createdBy?: string,
    public createdDate?: Date,
    public updateBy?: string,
    public updateDate?: Date,
    public password?: string,
    public accountDetailDTO?: AccountDetailDTO,
  ) {}
}
