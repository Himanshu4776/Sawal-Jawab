import React from 'react';
import { Github, Linkedin, Twitter } from 'lucide-react';

export const Footer: React.FC = () => {
  const currentYear = new Date().getFullYear();

  const socialLinks = [
    { 
      icon: Github, 
      href: "https://github.com/yourusername", 
      label: "GitHub" 
    },
    { 
      icon: Linkedin, 
      href: "https://linkedin.com/in/yourusername", 
      label: "LinkedIn" 
    },
    { 
      icon: Twitter, 
      href: "https://twitter.com/yourusername", 
      label: "Twitter" 
    }
  ];

  const footerLinks = [
    { label: "About", href: "/about" },
    { label: "Contact", href: "/contact" },
    { label: "Privacy Policy", href: "/privacy" },
    { label: "Terms of Service", href: "/terms" }
  ];

  return (
    <footer className="bg-gray-100 py-8 mt-auto border-t">
      <div className="max-w-6xl mx-auto px-4">
        <div className="grid md:grid-cols-3 gap-8">
          {/* Company Info */}
          <div>
            <h3 className="text-xl font-bold mb-4">Sawal Jawab Platform</h3>
            <p className="text-gray-600">
              A community-driven platform for sharing knowledge and getting answers.
            </p>
          </div>

          {/* Quick Links */}
          <div>
            <h4 className="font-semibold mb-4">Quick Links</h4>
            <nav className="space-y-2">
              {footerLinks.map((link) => (
                <a 
                  key={link.label} 
                  href={link.href} 
                  className="block text-gray-600 hover:text-blue-600 transition"
                >
                  {link.label}
                </a>
              ))}
            </nav>
          </div>

          {/* Social Links */}
          <div>
            <h4 className="font-semibold mb-4">Connect With Us</h4>
            <div className="flex space-x-4">
              {socialLinks.map((social) => (
                <a 
                  key={social.label}
                  href={social.href}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="text-gray-600 hover:text-blue-600 transition"
                  aria-label={social.label}
                >
                  <social.icon size={24} />
                </a>
              ))}
            </div>
          </div>
        </div>

        {/* Copyright */}
        <div className="text-center text-gray-500 mt-8 pt-4 border-t">
          <p>&copy; {currentYear} Sawal jawab Platform. All Rights Reserved.</p>
        </div>
      </div>
    </footer>
  );
};