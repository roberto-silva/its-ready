import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:ta_pronto/models/user.dart';
import 'package:ta_pronto/provider/users_provider.dart';
import 'package:ta_pronto/routes/app_routes.dart';

class UserTitle extends StatelessWidget {
  final User user;

  const UserTitle(this.user);

  @override
  Widget build(BuildContext context) {
    return ListTile(
      leading: CircleAvatar(
          child: IconButton(
        icon: Icon(Icons.person),
        color: Colors.white,
        onPressed: () {},
      )),
      title: Text(user.name),
      subtitle: Text(user.phone),
      trailing: Container(
        width: 100,
        child: Row(
          children: <Widget>[
            IconButton(
              icon: Icon(Icons.edit),
              color: Colors.blue,
              onPressed: () {
                Navigator.of(context).pushNamed(
                  AppRoutes.USER_FORM,
                  arguments: user,
                );
              },
            ),
            IconButton(
              icon: Icon(Icons.delete),
              color: Colors.red,
              onPressed: () {
                showDialog(
                  context: context,
                  builder: (context) => AlertDialog(
                    title: Text('Excluir Usuário'),
                    content: Text('Tem certeza ?'),
                    actions: <Widget>[
                      FlatButton(
                        onPressed: () {
                          Navigator.of(context).pop(false);
                        },
                        child: Text('Não'),
                      ),
                      FlatButton(
                        onPressed: () {
                          Navigator.of(context).pop(true);
                        },
                        child: Text('Sim'),
                      ),
                    ],
                  ),
                ).then((confirmed) => (confirmed)
                    ? Provider.of<UsersProvider>(context, listen: false)
                        .remove(user)
                    : null);
              },
            )
          ],
        ),
      ),
    );
  }
}
