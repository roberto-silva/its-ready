import 'package:flutter/material.dart';

class User {
  final String id;
  final String name;
  final String cpf;
  final String role;
  final String phone;
  final String email;

  const User(
      {this.id,
      @required this.name,
      @required this.email,
      @required this.cpf,
      @required this.role,
      @required this.phone});
}
