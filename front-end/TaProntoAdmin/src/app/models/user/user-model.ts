export class UserModel {
  id?: any;
  name?: string;
  cpf?: string;
  phone?: string;
  email?: string;
  address?: AddressModel;
  password?: string;
  profile?: number[];
  activated?: boolean;

  constructor(obj: any) {
    this.id = obj.id ? obj.id : null;
    this.name = obj.name ? obj.name : null;
    this.cpf = obj.cpf ? obj.cpf : null;
    this.phone = obj.phone ? obj.phone : null;
    this.email = obj.email ? obj.email : null;
    this.address = obj.address ? obj.address : null;
    this.password = obj.password ? obj.password : null;
    this.profile = obj.profile ? obj.profile : [];
    this.activated = obj.activated ? obj.activated : false;
  }
}

export class AddressModel {
  id?: any;
  cep?: string;
  city?: string;
  district?: string;
  street?: string;
  referencePoint?: string;
  activated?: boolean;

  constructor(obj: any) {
    this.id = obj.id ? obj.id : null;
    this.cep = obj.cep ? obj.cep : null;
    this.city = obj.city ? obj.city : null;
    this.district = obj.district ? obj.district : null;
    this.street = obj.street ? obj.street : null;
    this.referencePoint = obj.referencePoint ? obj.referencePoint : null;
    this.activated = obj.activated ? obj.activated : false;
  }
}
