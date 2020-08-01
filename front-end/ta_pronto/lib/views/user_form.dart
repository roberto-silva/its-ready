import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:ta_pronto/models/user.dart';
import 'package:ta_pronto/provider/users_provider.dart';

class UserForm extends StatelessWidget {
  final _form = GlobalKey<FormState>();
  final Map<String, String> _formData = {};

  void _loadFormData(User user) {
    _formData['id'] = user.id;
    _formData['name'] = user.name;
    _formData['email'] = user.email;
    _formData['cpf'] = user.cpf;
    _formData['role'] = user.role;
    _formData['phone'] = user.phone;
  }

  @override
  Widget build(BuildContext context) {
    final User user = ModalRoute.of(context).settings.arguments;
    if (user != null) {
      _loadFormData(user);
    }

    return Scaffold(
      appBar: AppBar(
        title: Text('Cadastro de Usuários'),
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.save),
            onPressed: () {
              if (_form.currentState.validate()) {
                _form.currentState.save();
                Provider.of<UsersProvider>(context, listen: false).put(
                  User(
                      name: _formData['name'],
                      email: _formData['email'],
                      cpf: _formData['cpf'],
                      role: _formData['role'],
                      phone: _formData['phone']),
                );
                Navigator.of(context).pop();
              }
            },
          )
        ],
      ),
      body: Container(
        child: new SingleChildScrollView(
          padding: EdgeInsets.fromLTRB(20, 0, 20, 0),
          child: Form(
            key: _form,
            child: Column(
              children: <Widget>[
                TextFormField(
                  initialValue: _formData['name'],
                  decoration: InputDecoration(labelText: 'Nome'),
                  validator: (value) {
                    if (value == null || value.trim().isEmpty) {
                      return "Campo obrigatório";
                    }
                    if (value.trim().length < 3) {
                      return "Valor mínimo 3 caracteres.";
                    }
                    return null;
                  },
                  onSaved: (value) => _formData['name'] = value,
                ),
                TextFormField(
                  initialValue: _formData['cpf'],
                  decoration: InputDecoration(labelText: 'CPF'),
                  validator: (value) {
                    if (value == null || value.trim().isEmpty) {
                      return "Campo obrigatório";
                    }
                    if (value.trim().length < 11) {
                      return "CPF inválido";
                    }
                    return null;
                  },
                  onSaved: (value) => _formData['cpf'] = value,
                ),
                TextFormField(
                  initialValue: _formData['email'],
                  decoration: InputDecoration(labelText: 'Email'),
                  validator: (value) {
                    if (value == null || value.trim().isEmpty) {
                      return "Campo obrigatório";
                    }
                    if (value.trim().length < 3) {
                      return "Valor mínimo 3 caracteres.";
                    }
                    return null;
                  },
                  onSaved: (value) => _formData['email'] = value,
                ),
                TextFormField(
                  initialValue: _formData['phone'],
                  decoration: InputDecoration(labelText: 'Telefone'),
                  validator: (value) {
                    if (value == null || value.trim().isEmpty) {
                      return "Campo obrigatório";
                    }
                    if (value.trim().length < 11) {
                      return "Valor mínimo 11 caracteres.";
                    }
                    return null;
                  },
                  onSaved: (value) => _formData['phone'] = value,
                ),
                TextFormField(
                  initialValue: _formData['role'],
                  decoration: InputDecoration(labelText: 'Cargo'),
                  validator: (value) {
                    if (value == null || value.trim().isEmpty) {
                      return "Campo obrigatório";
                    }
                    if (value.trim().length < 3) {
                      return "Valor mínimo 3 caracteres.";
                    }
                    return null;
                  },
                  onSaved: (value) => _formData['role'] = value,
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
