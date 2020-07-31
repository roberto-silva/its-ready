import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:ta_pronto/components/user_title.dart';
import 'package:ta_pronto/models/user.dart';
import 'package:ta_pronto/provider/users_provider.dart';
import 'package:ta_pronto/routes/app_routes.dart';

class UserList extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final UsersProvider users = Provider.of(context);
    return Scaffold(
      appBar: AppBar(
        title: Text('Usuários Cadastrados'),
        actions: <Widget>[
          IconButton(
            icon: Icon(Icons.add),
            onPressed: () {
              Navigator.of(context).pushNamed(AppRoutes.USER_FORM);
              // users.put(User(
              //     name: 'Test Edit',
              //     cpf: '000.000.000-00',
              //     email: 'email@test.com',
              //     phone: '(00) 00000-0000',
              //     role: 'Super User'));
              // users.remove(users.byIndex(0));
            },
          )
        ],
      ),
      body: ListView.builder(
          itemCount: users.count,
          itemBuilder: (ctx, i) => UserTitle(users.byIndex(i))),
    );
  }
}
